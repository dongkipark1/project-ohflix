package com.project.ohflix.domain.like;

import com.project.ohflix._core.utils.ApiUtil;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeApiController {
    private final HttpSession session;
    private final LikeService likeService;

    // 좋아요 상태 조회
    @GetMapping("/api/like-status/{contentId}")
    public ResponseEntity<?> getLikeStatus (@PathVariable Integer contentId){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser!= null) {
            userId = sessionUser.getId();
        }
        LikeResponse.LikeStatus respDTO =likeService.getLikeStatus(contentId, userId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 좋아요 하기
    @PostMapping("/api/users/{contentId}/like")
    public ResponseEntity<?> addLike(@PathVariable Integer contentId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser!= null) {
            userId = sessionUser.getId();
        }
        LikeResponse.AddLikeDTO respDTO = likeService.addLike(contentId, userId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 좋아요 취소 기능
    @PostMapping("/api/users/{contentId}/dislike")
    public String removeLike(@PathVariable Integer contentId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser!= null) {
            userId = sessionUser.getId();
        }
        likeService.removeLike(contentId, userId);

        return "redirect:/api/main-page";
    }
}
