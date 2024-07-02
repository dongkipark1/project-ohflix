package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.cardInfo.CardInfoResponse;
import com.project.ohflix.domain.cardInfo.CardInfoService;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.content.ContentService;
import com.project.ohflix.domain.user.SessionUser;
import com.project.ohflix.domain.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class PurchaseHistoryController {

    private final HttpSession session;
    private final PurchaseHistoryService purchaseHistoryService;
    private final ContentRepository contentRepository;
    private final ContentService contentService;
    private final CardInfoService cardInfoService;
    private final UserRepository userRepository;
    private final PurchaseHistoryNativeRepository purchaseHistoryNativeRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(PurchaseHistoryService.class);

    @GetMapping("/api/membership/payment")
    public String membershipPayment(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<CardInfoResponse.paymethodManageDTO> respDTO = purchaseHistoryService.paymethodManagePage(sessionUser.getId());
        request.setAttribute("paymethodManageDTO", respDTO);
        return "paymethod/membership-pay-form";
    }

    @GetMapping("/api/paymethod-form")
    public String getPaymethodRegisterForm() {
        return "paymethod/paymethod-register-form";
    }

    @GetMapping("/api/paymethod-manage")
    public String getPaymethodManage(HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<CardInfoResponse.paymethodManageDTO> respDTO = purchaseHistoryService.paymethodManagePage(sessionUser.getId());
        request.setAttribute("paymethodManageDTO", respDTO);
        return "paymethod/paymethod-manage";
    }

    @GetMapping("/api/paymethod-update-form/{cardInfoId}")
    public String getPaymethodUpdateForm(@PathVariable("cardInfoId") Integer cardInfoId, HttpServletRequest request) {
        CardInfoResponse.DetailDTO respDTO = cardInfoService.findCardInfoById(cardInfoId);
        request.setAttribute("DetailDTO", respDTO);
        return "paymethod/paymethod-update-form";
    }

    @GetMapping("/api/purchase-histories")
    public String getPayment(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PurchaseHistoryResponse.purchaseHistoryDTO respDTO = purchaseHistoryService.purchaseHistories(sessionUser.getId());
        request.setAttribute("purchaseHistoryDTO", respDTO);

        return "paymethod/purchase-histories";
    }


    @GetMapping("/api/account-security")
    public String accountSecurity(HttpServletRequest request) {
        // accountSecurityPage 데이터 바인딩
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser != null) {
            userId = sessionUser.getId();
        }
        PurchaseHistoryResponse.AccountSecurityDTO respDTO = purchaseHistoryService.accountSecurityPage(userId);
        request.setAttribute("AccountSecurityDTO", respDTO);

        return "account/account-security";
    }


    @GetMapping("/admin/content-update-link")
    public String contentUpdateLink(HttpServletRequest request) {
        // contentUpdateLinkPage 데이터 바인딩
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        ContentResponse.ContentUpdateLinkPageDTO respDTO = contentService.contentUpdateLinkPageDTO(sessionAdmin.getId());
        request.setAttribute("ContentUpdateLinkDTO", respDTO);

        return "admin/content-update-link";
    }

    @GetMapping("/api/video-manage")
    public String videoManage(HttpServletRequest request) {
        // videoManagePage 데이터 바인딩
        ContentResponse.VideoManagePageDTO respDTO = contentService.videoManagePageDTO();
        request.setAttribute("VideoManagePageDTO", respDTO);

        return "admin/video-manage";
    }


    @PostMapping("/upload/movie")
    public String uploadMovie() {
        return null;
    }



    @PostMapping("/update/movie")
    public String updateMovie() {
        return null;
    }

    @PostMapping("/update/info")
    public String updateInfo() {
        return null;
    }

    @PostMapping("/kakaoPay/ready")
    @ResponseBody
    public ResponseEntity<Map<String, String>> readyToKakaoPay(@RequestBody Map<String, Object> request, HttpSession session) {
        int userId = (int) request.get("userId");
        String itemName = (String) request.get("itemName");
        int totalAmount = (int) request.get("totalAmount");
        int vatAmount = (int) request.get("vatAmount");

        PurchaseHistoryResponse.KakaoPayReadyDTO kakaoPayReadyDTO = purchaseHistoryService.preparePayment(userId, itemName, totalAmount, vatAmount);

        // 세션에 tid 저장
        session.setAttribute("tid", kakaoPayReadyDTO.getTid());

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", kakaoPayReadyDTO.getNextRedirectPcUrl());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/kakaoPaySuccess")
    public ModelAndView paymentSuccess(@RequestParam(value = "pg_token", required = true) String pgToken) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        int userId = sessionUser.getId();
        String tid = (String) session.getAttribute("tid");

        // 결제 승인 처리
        PurchaseHistoryResponse.KakaoPayApproveDTO approveDTO = purchaseHistoryService.approvePayment(userId, tid, pgToken);

        // 결제 내역 저장
        purchaseHistoryService.savePurchaseHistory(userId, approveDTO);

        // 성공 페이지 리다이렉트
        ModelAndView modelAndView = new ModelAndView("paymethod/pay-success");
        modelAndView.addObject("pgToken", pgToken);
        modelAndView.addObject("approveDTO", approveDTO);
        return modelAndView;
    }

    @GetMapping("/api/errorPage")
    public ModelAndView errorPage() {
        return new ModelAndView("paymethod/error-page");
    }

    @GetMapping("/api/kakaoPayFail")
    public String kakaoPayFail() {
        return "/paymethod/pay-fail"; // 실제로는 결제 실패 페이지로 리다이렉트합니다.
    }

    @GetMapping("/api/kakaoPayCancel")
    public String kakaoPayCancel() {
        return "/paymethod/pay-cancel"; // 실제로는 결제 취소 페이지로 리다이렉트합니다.
    }


    // 정기 결제 요청 API 엔드포인트
    @PostMapping("/api/payment/subscription")
    @ResponseBody
    public Map<String, Object> subscriptionPayment(@RequestParam int userId, @RequestParam String sid, @RequestParam int totalAmount) {
        return purchaseHistoryService.subscriptionPayment(userId, sid, totalAmount);
    }

    // 정기 결제 비활성화 API 엔드포인트
    @PostMapping("/api/payment/deactivate")
    @ResponseBody
    public Map<String, Object> deactivateSubscription(@RequestParam int userId, @RequestParam String sid) {
        return purchaseHistoryService.deactivateSubscription(userId, sid);
    }

    // 정기 결제 상태 조회 API 엔드포인트
    @PostMapping("/api/payment/status")
    @ResponseBody
    public Map<String, Object> checkSubscriptionStatus(@RequestParam int userId, @RequestParam String sid) {
        return purchaseHistoryService.checkSubscriptionStatus(userId, sid);
    }
}
