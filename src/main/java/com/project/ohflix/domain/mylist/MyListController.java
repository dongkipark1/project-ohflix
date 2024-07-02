package com.project.ohflix.domain.mylist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ohflix._core.utils.ApiUtil;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.user.SessionUser;
import com.project.ohflix.domain.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Controller
public class MyListController {
    private final HttpSession session;
    private final MyListService myListService;
    private final UserService userService;
    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper;



    @PostMapping("/api/my-favorite-list")
    @ResponseBody
    public ResponseEntity<List<MyListResponse.ContentDTO>> sendRequestToOpenAI(@RequestBody MyListRequest.OpenAIRequest openAIRequest) {
        return myListService.processOpenAIRequest(openAIRequest);
    }



    @GetMapping("/api/my-favorite-list")
    public String getMyFavList(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        MyListResponse.MyListDTO respDTO = myListService.findMyListById(sessionUser.getId());
        List<Content> openAIRequest =myListService.getOpenAi();
        System.out.println("openAIRequest = " + openAIRequest);
        request.setAttribute("MyListDTO", respDTO);
        request.setAttribute("openAIRequest", openAIRequest);
        return "mylist/my-favorite-list";
    }


    @PostMapping("/api/users/{contentId}/favorite")
    public ResponseEntity<?> addFavorite(@PathVariable int contentId, MyListRequest.AddFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        myListService.addFavorite(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(true));  // 찜 성공 여부를 JSON으로 반환
    }

    @PostMapping("/api/users/{contentId}/unfavorite")
    public ResponseEntity<?> removeFavorite(@PathVariable int contentId, MyListRequest.RemoveFavoriteDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);

        myListService.removeFavorite(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(true));  // 찜 취소 성공 여부를 JSON으로 반환
    }
}
