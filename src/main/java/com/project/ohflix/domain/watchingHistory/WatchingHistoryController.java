package com.project.ohflix.domain.watchingHistory;

import com.project.ohflix.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class WatchingHistoryController {
    private final HttpSession session;
    private final WatchingHistoryService watchingHistoryService;


    @GetMapping("/api/view-history")
    public String getViewed(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser sessionAdmin = (SessionUser) session.getAttribute("sessionAdmin");
        Integer userId = null;
        if (sessionAdmin != null) {
            userId = sessionAdmin.getId();
        }
        if (sessionUser != null) {
            userId = sessionUser.getId();
        }
        WatchingHistoryResponse.WatchingHistoryDTO respDTO =watchingHistoryService.getWatchingHistory(userId);
        request.setAttribute("watchingHistory", respDTO);
        return "user/view-history";
    }
}
