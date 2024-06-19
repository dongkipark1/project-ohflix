package com.project.ohflix.domain.purchaseHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PurchaseHistoryController {

    @GetMapping("/api/paymethod-form")
    public String getPaymethodRegisterForm() {
        return "paymethod/paymethod-register-form";
    }

    @GetMapping("/api/paymethod-manage")
    public String getPaymethodManage() {return "paymethod/paymethod-manage";
    }

    @GetMapping("/api/paymethod-update-form")
    public String getPaymethodUpdateForm() {
        return "paymethod/paymethod-update-form";
    }

    @GetMapping("/api/purchase-histories")
    public String getPayment() {
        return "paymethod/purchase-histories";
    }

    @GetMapping("/api/profile-setting")
    public String profileSetting() {
        return "profile/profile-setting";
    }


    @GetMapping("/api/account-security")
    public String accountSecurity() {
        return "account/account-security";
    }

    @GetMapping("/api/admin-upload")
    public String adminUpload() {
        return "admin-upload";
    }

    @PostMapping("/upload/movie")
    public String uploadMovie() {
        return null;
    }

    @PostMapping("/upload/info")
    public String uploadInfo() {
        return null;
    }
}