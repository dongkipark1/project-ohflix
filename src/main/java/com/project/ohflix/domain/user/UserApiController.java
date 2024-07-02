package com.project.ohflix.domain.user;

import com.project.ohflix._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    // 구독 여부체크
    @GetMapping("/api/check-subscription")
    public ResponseEntity<?> checkSubscription(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser != null) {
            userId = sessionUser.getId();
        }
        UserResponse.IsSubscribed respDTO = userService.checkSubscription(userId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 시청제한 페이지에서 사용자의 관람등급 설정 가져오기
    @GetMapping("/api/user-restriction-info")
    public ResponseEntity<?> getUserRestrictionInfo(HttpSession session){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser"); // TODO : 여기에 나중에 진짜 세션 아이디 넣어야됨
        UserResponse.RestrictionLevelDTO respDTO = userService.UserRestrictionInfo(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
