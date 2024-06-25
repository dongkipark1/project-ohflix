package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.cardInfo.CardInfoResponse;
import com.project.ohflix.domain.cardInfo.CardInfoService;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.content.ContentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PurchaseHistoryController {

    private final HttpSession session;
    private final PurchaseHistoryService purchaseHistoryService;
    private final ContentRepository contentRepository;
    private final ContentService contentService;
    private final CardInfoService cardInfoService;

    @GetMapping("/api/paymethod-form")
    public String getPaymethodRegisterForm() {
        return "paymethod/paymethod-register-form";
    }

    @GetMapping("/api/paymethod-manage")
    public String getPaymethodManage(HttpServletRequest request) {

        //SessionUser user=session.getAttribute("sessionUser");
        //유저정보를 넣을 수 없어서 2번유저를 바로 넣음!
        List<CardInfoResponse.paymethodManageDTO> respDTO = purchaseHistoryService.paymethodManagePage(2);
        request.setAttribute("paymethodManageDTO", respDTO);
        return "paymethod/paymethod-manage";
    }

    @GetMapping("/api/paymethod-update-form/{cardInfoId}")
    public String getPaymethodUpdateForm(@PathVariable("cardInfoId") Integer cardInfoId,  HttpServletRequest request) {
        CardInfoResponse.DetailDTO respDTO = cardInfoService.findCardInfoById(cardInfoId);
        request.setAttribute("DetailDTO", respDTO);
        return "paymethod/paymethod-update-form";
    }

    @GetMapping("/api/purchase-histories")
    public String getPayment(HttpServletRequest request) {

        //SessionUser user=session.getAttribute("sessionUser");
        //유저정보를 넣을 수 없어서 2번유저를 바로 넣음!
        PurchaseHistoryResponse.purchaseHistoryDTO respDTO = purchaseHistoryService.purchaseHistories(2);
        request.setAttribute("purchaseHistoryDTO", respDTO);

        return "paymethod/purchase-histories";
    }



    @GetMapping("/api/account-security")
    public String accountSecurity() {
        // accountSecurityPage 데이터 바인딩
        PurchaseHistoryResponse.AccountSecurityDTO respDTO = purchaseHistoryService.accountSecurityPage(2);
        session.setAttribute("AccountSecurityDTO", respDTO);

        return "account/account-security";
    }


    @GetMapping("/admin/content-update-link")
    public String contentUpdateLink() {
        // contentUpdateLinkPage 데이터 바인딩
        ContentResponse.ContentUpdateLinkPageDTO respDTO = contentService.contentUpdateLinkPageDTO(1);
        session.setAttribute("ContentUpdateLinkDTO", respDTO);

        return "admin/content-update-link";
    }

    @GetMapping("/api/video-manage")
    public String videoManage() {
        // videoManagePage 데이터 바인딩
        ContentResponse.VideoManagePageDTO respDTO = contentService.videoManagePageDTO();
        session.setAttribute("VideoManagePageDTO", respDTO);

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
}
