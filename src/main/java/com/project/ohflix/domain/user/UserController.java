package com.project.ohflix.domain.user;


import com.project.ohflix._core.error.exception.Exception401;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix._core.utils.EnumEditor;
import com.project.ohflix.domain._enums.Reason;
import com.project.ohflix.domain.refund.RefundRequest;
import com.project.ohflix.domain.refund.RefundService;
import com.project.ohflix.domain.watchingHistory.WatchingHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.SessionInitializerFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;
    private final RefundService refundService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final WatchingHistoryService watchingHistoryService;

    @GetMapping("/login-form")
    public String getLoginForm(Model model) {

        return "user/login-form";
    }

    // kakao 로그인
    // http://localhost:8080/oauth/kakao/callback
    @GetMapping("/oauth/kakao/callback")
    public String oauthKakaoCallback(String code) {

        User user = userService.kakaoLogin(code);
        SessionUser sessionUser = new SessionUser(user);
        System.out.println("👉👉👉👉👉👉👉👉👉" + code);
        redisTemplate.opsForValue().set("sessionUser", sessionUser);
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/api/main-page";
    }


    // 사용자 환불요청 페이지
    @GetMapping("/api/refund-request-form")
    public String getAccountRefundPage(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.RefundRequestPageDTO respDTO = userService.refundRequestPage(sessionUser.getId());
        request.setAttribute("RefundRequestPageDTO", respDTO);
        return "account/refund-request-form";
    }

    // 환불 액션
    @PostMapping("/refund")
    public String refund(RefundRequest.RequestDTO reqDTO) {
        userService.requestRefund(reqDTO);
        return "redirect:/api/account-view";
    }


    // 사용자 확인 방법 선택 페이지
    @GetMapping("/api/user-check")
    public String getUserCheck(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.UserCheckDTO respDTO = userService.userCheckPage(sessionUser.getId());
        request.setAttribute("UserCheckDTO", respDTO);
        return "user/user-check";
    }

    // 사용자 프로필 변경 페이지
    @GetMapping("/api/profile-form")
    public String getProfileView(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.ProfileFormDTO respDTO = userService.userProfileForm(sessionUser.getId());
        request.setAttribute("UserProfileFormDTO", respDTO);
        return "profile/profile-form";
    }

    // YSH : 멥버십 취소 페이지
    @GetMapping("/api/cancel-plan")
    public String getCancelPlan(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.CancelPlanPageDTO respDTO = userService.userCancelPlan(sessionUser.getId());

        request.setAttribute("CancelPlanPageDTO", respDTO);
        return "user/cancel-plan";
    }

    // YSH : 멤버십 상세정보 페이지
    @GetMapping("/api/account-membership")
    public String getAccountMembership(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.AccountMembershipDTO respDTO = userService.accountMembership(sessionUser.getId());
        request.setAttribute("AccountMembershipDTO", respDTO);
        return "account/account-membership";
    }


    @PostMapping("/api/cancel-plan")
    @ResponseBody
    public ResponseEntity<?> cancelPlan() {
        try {
            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
            userService.cancelMembership(sessionUser.getId());
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("success", false));
        }
    }



    // 비밀번호 변경 페이지
    @GetMapping("/api/password-change-form")
    public String getPasswordChangeForm(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.PasswordChangePageDTO respDTO = userService.passwordChangePage(sessionUser.getId());
        request.setAttribute("passwordChangePageDTO", respDTO);
        return "user/password-change-form";
    }

    // 비밀번호 변경
    @PostMapping("/update/password")
    public String updatePassword(UserRequest.UpdatePasswordDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        userService.updatePassword(reqDTO, sessionUser.getId());

        return "redirect:/login-form";
    }

    @GetMapping("/api/restriction-pass")
    public String getRestrictionPass() {
        return "restriction/restriction-pass";
    }

    @GetMapping("/api/restriction-manage")
    public String getRestrictionManage() {
        return "restriction/restriction-manage";
    }


    @GetMapping("/api/account-view")
    public String getAccountPage(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.AccountMembershipInfoDTO respDTO = userService.accountMembershipInfo(sessionUser.getId());
        request.setAttribute("accountMembershipInfoDTO", respDTO);
        return "account/account-view";
    }

    @GetMapping("/api/profile-setting")
    public String profileSetting(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.ProfileSettingDTO respDTO = userService.profileSetting(sessionUser.getId());
        request.setAttribute("ProfileSettingDTO", respDTO);
        return "profile/profile-setting";
    }


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO, Model model) {
        try {
            SessionUser sessionUser = userService.login(requestDTO);
            redisTemplate.opsForValue().set("sessionUser", sessionUser);
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/api/main-page";
        } catch (Exception401 e) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("email", requestDTO.getEmail()); // 이메일 유지
            return "user/login-form";
        } catch (Exception404 e) {
            model.addAttribute("error", "유저 정보가 없습니다.");
            model.addAttribute("email", requestDTO.getEmail()); // 이메일 유지
            return "user/login-form";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        redisTemplate.delete("sessionUser");
        session.invalidate();

        return "redirect:/login-form";
    }


    // enum 스트링을 다시 enum으로 바꾸기 위해서 이게 필요함
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Reason.class, new EnumEditor<>(Reason.class));
        // 다른 enum 타입에 대해 추가 등록 가능
        // binder.registerCustomEditor(AnotherEnum.class, new EnumEditor<>(AnotherEnum.class));
    }

    // 회원가입 페이지
    @GetMapping("/signup-page")
    public String singUpPage() {
        return "user/sign-up-page";
    }

    @PostMapping("/signup")
    public String singUpPost(UserRequest.SignupDTO reqDTO) {
        // 회원가입 시키기
        userService.signup(reqDTO);

        try {
            // 로그인 시키기
            UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO();
            loginDTO.setEmail(reqDTO.getEmail());
            loginDTO.setPassword(reqDTO.getPassword());

            SessionUser sessionUser = userService.login(loginDTO);
            redisTemplate.opsForValue().set("sessionUser", sessionUser);
            session.setAttribute("sessionUser", sessionUser);

            return "redirect:/signup-page-step2";
        } catch (Exception e) {
            throw new Exception401("로그인 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/signup-page-step2")
    public String singUpPageStep2() {

        return "user/sign-up-page-step2";
    }
}
