package com.project.ohflix.domain.purchaseHistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ohflix._core.config.KakaoPayConfig;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.cardInfo.CardInfoRepository;
import com.project.ohflix.domain.cardInfo.CardInfoResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {

    private final UserRepository userRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final CardInfoRepository cardInfoRepository;
    private final ContentRepository contentRepository;
    private final RestTemplate restTemplate;
    private final HttpHeaders kakaoPayHeaders;
    private final KakaoPayConfig kakaoPayConfig;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(PurchaseHistoryService.class);

    //paymethod-manage
    public List<CardInfoResponse.paymethodManageDTO> paymethodManagePage(int userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));

        List<CardInfo> cardInfos = cardInfoRepository.findByUserId(user.getId()).orElseThrow(() -> new Exception404("등록하신 카드가 없습니다."));

        List<CardInfoResponse.paymethodManageDTO> respDTO = cardInfos.stream().map(CardInfoResponse.paymethodManageDTO::new).toList();

        return respDTO;
    }

    //purchase-histories
    public PurchaseHistoryResponse.purchaseHistoryDTO purchaseHistories(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception404("유저 정보가 없습니다."));
        //1년 계산
        Timestamp oneYearAgo = Timestamp.valueOf(LocalDateTime.now().minusYears(1));
        //결제내역기간 1년미만만 조회
        List<PurchaseHistory> purchaseHistories= purchaseHistoryRepository.findByUserId(user.getId(), oneYearAgo).orElseThrow(() -> new Exception404("결제내역이 없습니다."));



        return new PurchaseHistoryResponse.purchaseHistoryDTO(purchaseHistories, user);
    }

    // AccountSecurityPage (email, mobile 불러오기)
    @Transactional
    public PurchaseHistoryResponse.AccountSecurityDTO accountSecurityPage(int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));

        return new PurchaseHistoryResponse.AccountSecurityDTO(user);
    }

    // 결제 준비
    public PurchaseHistoryResponse.KakaoPayReadyDTO preparePayment(int userId, String itemName, int totalAmount, int vatAmount) {
        String url = "https://kapi.kakao.com/v1/payment/ready";

        String cid = kakaoPayConfig.getCid();
        if (cid == null) {
            logger.error("CID can't be null.");
            throw new IllegalArgumentException("CID can't be null.");
        }

        logger.info("Preparing payment with CID: {}", cid);
        System.out.println("CID: " + cid);
        System.out.println("AdminKey: " + kakaoPayConfig.getAdminKey());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK " + kakaoPayConfig.getAdminKey());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", cid);
        String partnerOrderId = "order_id_" + userId;
        body.add("partner_order_id", partnerOrderId);
        body.add("partner_user_id", String.valueOf(userId));
        body.add("item_name", itemName);
        body.add("quantity", "1");
        body.add("total_amount", String.valueOf(totalAmount));
        body.add("vat_amount", String.valueOf(vatAmount));
        body.add("tax_free_amount", "0");
        body.add("approval_url", kakaoPayConfig.getRedirectUrl() + "/api/kakaoPaySuccess");
        body.add("fail_url", kakaoPayConfig.getRedirectUrl() + "/api/kakaoPayFail");
        body.add("cancel_url", kakaoPayConfig.getRedirectUrl() + "/api/kakaoPayCancel");

        logger.info("Request body: {}", body);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
            logger.info("KakaoPay ready response: {}", response);
        } catch (HttpClientErrorException e) {
            logger.error("HttpClientErrorException: Status code: {}, Response body: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;
        }

        Map<String, Object> responseBody = response.getBody();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime createdAt = LocalDateTime.parse((String) responseBody.get("created_at"), formatter);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("tid", responseBody.get("tid"));
        session.setAttribute("partner_order_id", partnerOrderId);
        session.setAttribute("partner_user_id", String.valueOf(userId));


        return new PurchaseHistoryResponse.KakaoPayReadyDTO(
                (String) responseBody.get("tid"),
                (String) responseBody.get("next_redirect_app_url"),
                (String) responseBody.get("next_redirect_mobile_url"),
                (String) responseBody.get("next_redirect_pc_url"),
                (String) responseBody.get("android_app_scheme"),
                (String) responseBody.get("ios_app_scheme"),
                Timestamp.valueOf(createdAt)
        );
    }

    // 결제 승인
    public PurchaseHistoryResponse.KakaoPayApproveDTO approvePayment(int userId, String tid, String pgToken) {
        String url = "https://kapi.kakao.com/v1/payment/approve";

        String cid = kakaoPayConfig.getCid();
        if (cid == null) {
            logger.error("CID can't be null.");
            throw new IllegalArgumentException("CID can't be null.");
        }

        logger.info("Approving payment with CID: {}", cid);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", cid);
        body.add("tid", tid);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        String partnerOrderId = (String) session.getAttribute("partner_order_id");
        String partnerUserId = (String) session.getAttribute("partner_user_id");
        if (partnerOrderId == null || partnerUserId == null) {
            throw new IllegalStateException("Partner order ID or user ID not found in session.");
        }

        body.add("partner_order_id", partnerOrderId);
        body.add("partner_user_id", partnerUserId);
        body.add("pg_token", pgToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK " + kakaoPayConfig.getAdminKey());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                logger.error("Failed to approve payment. Status code: {}, Response: {}", response.getStatusCode(), response.getBody());
                throw new HttpClientErrorException(response.getStatusCode(), "Failed to approve payment");
            }

            logger.info("KakaoPay approve response: {}", response);

            // 결제 승인 정보를 DTO로 반환
            Map<String, Object> responseBody = response.getBody();
            Map<String, Object> amountMap = (Map<String, Object>) responseBody.get("amount");
            Map<String, Object> cardInfoMap = (Map<String, Object>) responseBody.get("card_info");

            PurchaseHistoryResponse.KakaoPayApproveDTO.Amount amount = new PurchaseHistoryResponse.KakaoPayApproveDTO.Amount(
                    (Integer) amountMap.get("total"),
                    (Integer) amountMap.get("tax_free"),
                    (Integer) amountMap.get("vat"),
                    (Integer) amountMap.get("point"),
                    (Integer) amountMap.get("discount"),
                    (Integer) amountMap.get("green_deposit")
            );

            PurchaseHistoryResponse.KakaoPayApproveDTO.PaymentCardInfo cardInfo = null;
            if (cardInfoMap != null) {
                cardInfo = new PurchaseHistoryResponse.KakaoPayApproveDTO.PaymentCardInfo(
                        (String) cardInfoMap.get("kakaopay_purchase_corp"),
                        (String) cardInfoMap.get("kakaopay_purchase_corp_code"),
                        (String) cardInfoMap.get("kakaopay_issuer_corp"),
                        (String) cardInfoMap.get("kakaopay_issuer_corp_code"),
                        (String) cardInfoMap.get("bin"),
                        (String) cardInfoMap.get("card_type"),
                        (String) cardInfoMap.get("install_month"),
                        (String) cardInfoMap.get("approved_id"),
                        (String) cardInfoMap.get("card_mid"),
                        (String) cardInfoMap.get("interest_free_install"),
                        (String) cardInfoMap.get("installment_type"),
                        (String) cardInfoMap.get("card_item_code")
                );
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime createdAt = LocalDateTime.parse((String) responseBody.get("created_at"), formatter);
            LocalDateTime approvedAt = LocalDateTime.parse((String) responseBody.get("approved_at"), formatter);

            PurchaseHistoryResponse.KakaoPayApproveDTO approveDTO = new PurchaseHistoryResponse.KakaoPayApproveDTO(
                    (String) responseBody.get("aid"),
                    (String) responseBody.get("tid"),
                    (String) responseBody.get("cid"),
                    (String) responseBody.get("sid"),
                    (String) responseBody.get("partner_order_id"),
                    (String) responseBody.get("partner_user_id"),
                    (String) responseBody.get("payment_method_type"),
                    amount,
                    cardInfo,
                    (String) responseBody.get("item_name"),
                    (String) responseBody.get("item_code"),
                    (Integer) responseBody.get("quantity"),
                    Timestamp.valueOf(createdAt),
                    Timestamp.valueOf(approvedAt)
            );

            // 결제 내역 저장
            savePurchaseHistory(userId, approveDTO);
            updateSubscriptionStatus(userId, true);

            return approveDTO;

        } catch (HttpClientErrorException e) {
            logger.error("HttpClientErrorException: Status code: {}, Response body: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;
        } catch (RestClientException e) {
            logger.error("RestClientException: {}", e.getMessage());
            throw e;
        }
    }

    // 카카오페이 결제 정보 저장
    @Transactional
    public void savePurchaseHistory(int userId, PurchaseHistoryResponse.KakaoPayApproveDTO approveDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception404("User not found"));
        PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                .user(user)
                .description("스트리밍 서비스")
                .servicePeriod(approveDTO.getCreatedAt().toString() + " - " + approveDTO.getApprovedAt().toString())
                .paymethod(Paymethod.KAKAOPAY)
                .amount(13500)
                .createdAt(approveDTO.getApprovedAt())
                .build();
        purchaseHistoryRepository.save(purchaseHistory);
    }

    // 구독 상태 업데이트 메서드
    @Transactional
    public void updateSubscriptionStatus(int userId, boolean isSubscribe) {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception404("User not found"));
        user.setSubscribe(isSubscribe);
        userRepository.save(user);
    }

    // 정기 결제 요청
    public Map<String, Object> subscriptionPayment(int userId, String sid, int totalAmount) {
        String url = "https://open-api.kakaopay.com/v1/payment/subscription";

        Map<String, Object> body = Map.of(
                "cid", kakaoPayConfig.getCid(),
                "sid", sid,
                "partner_order_id", "order_id_" + userId,
                "partner_user_id", String.valueOf(userId),
                "item_name", "정기결제",
                "quantity", 1,
                "total_amount", totalAmount,
                "vat_amount", 12150,
                "tax_free_amount", 0
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, kakaoPayHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        return response.getBody();
    }

    // 정기 결제 비활성화
    public Map<String, Object> deactivateSubscription(int userId, String sid) {
        String url = "https://open-api.kakaopay.com/v1/payment/manage/subscription/inactive";

        Map<String, Object> body = Map.of(
                "cid", kakaoPayConfig.getCid(),
                "sid", sid
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, kakaoPayHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        return response.getBody();
    }

    // 정기 결제 상태 조회
    public Map<String, Object> checkSubscriptionStatus(int userId, String sid) {
        String url = "https://open-api.kakaopay.com/v1/payment/manage/subscription/status";

        Map<String, Object> body = Map.of(
                "cid", kakaoPayConfig.getCid(),
                "sid", sid
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, kakaoPayHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        return response.getBody();
    }
}





