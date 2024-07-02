package com.project.ohflix.domain.like;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.mylist.MyListRequest;
import com.project.ohflix.domain.user.SessionUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

@SpringBootTest
public class LikeControllerTest {

    @Mock
    private MockHttpSession session;

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void addLike_test(){
        // given
        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(2);
        int contentId = 1;
        session.setAttribute("sessionUser", sessionUser);

        // DTO 생성 및 초기화
        MyListRequest.RemoveFavoriteDTO reqDTO = new MyListRequest.RemoveFavoriteDTO();
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);
        reqDTO.setWatchOrFav(WatchOrFav.WATCHING);

//        String reqBody = om.writeValueAsString(reqDTO);
//        System.out.println("( 요청 ) reqBody: " + reqBody);

        // when


        // then

    }
}
