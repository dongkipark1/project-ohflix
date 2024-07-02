package com.project.ohflix.domain.mylist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.mylist.MyListResponse.AddFavoriteDTO;
import com.project.ohflix.domain.user.SessionUser;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@Sql("classpath:db/data.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MylistControllerTest {

    @Autowired
    private MyListService myListService;

    @Autowired
    private MockMvc mvc;

    @Mock
    private MockHttpSession session;

    @Autowired
    private ObjectMapper om;
//
//    @Autowired
//    private EntityManager em;
//
//    @BeforeEach
//    public void setUp() {
//        em.clear();
//    }

    // 찜하기 추가
    @Test
    public void addFavorite_test() throws Exception {
        // given
        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(2);
        int contentId = 1;
        session.setAttribute("sessionUser", sessionUser);

        // DTO 생성 및 초기화
        MyListRequest.AddFavoriteDTO reqDTO = new MyListRequest.AddFavoriteDTO();
        reqDTO.setUserId(sessionUser.getId());
        reqDTO.setContentId(contentId);
        reqDTO.setWatchOrFav(WatchOrFav.FAVORITE);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("( 요청 ) reqBody: " + reqBody);

//        // Mockito 테스트
//        Mockito.when(myListService.addFavorite(Mockito.any(MyListRequest.AddFavoriteDTO.class)))
//                .thenReturn(new MyListResponse.AddFavoriteDTO(reqDTO));

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders.post("/api/users/{contentId}/favorite", contentId)
                        .session(session)
                        .contentType("application/json")
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("( 응답 ) respBody: " + respBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").isEmpty());
    }

    // 찜하기 취소
    @Test
    public void removeFavorite_test() throws Exception {
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

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("( 요청 ) reqBody: " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders.post("/api/users/{contentId}/unfavorite", contentId)
                        .session(session)
                        .contentType("application/json")
                        .content(reqBody)
        );
        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("( 응답 ) respBody: " + respBody);


        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").isEmpty());
    }

    // 내가 찜한 콘텐츠
    @Test
    public void getFavorite_test() throws Exception {

        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(2);
        session.setAttribute("sessionUser", sessionUser);

        // GET 요청 및 검증
        MvcResult result = mvc.perform(get("/api/my-favorite-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("mylist/my-favorite-list"))
                .andReturn();

        // HttpServletRequest 검증
        HttpServletRequest request = result.getRequest();
        MyListResponse.MyListDTO myListDTO =
                (MyListResponse.MyListDTO) request.getAttribute("MyListDTO");

        assertThat(myListDTO.getMyFavoriteList()).isNotNull();
        assertThat(myListDTO.getMyFavoriteList().size()).isEqualTo(20); // 데이터 개수 검증
        assertThat(myListDTO.getMyFavoriteList().get(0).getContent().getId()).isEqualTo("1"); // 첫 번째 아이콘의 이름 검증
        assertThat(myListDTO.getMyFavoriteList().get(0).getContent().getTitle()).isEqualTo("레디 플레이어 원"); // 첫 번째 아이콘의 경로 검증
    }
//    @Test
//    public void getMyFavList_test() throws Exception {
//        // given
//        SessionUser sessionUser = new SessionUser();
//        sessionUser.setId(2);
//        session.setAttribute("sessionUser", sessionUser);
//
//        MyListResponse.MyListDTO respDTO = new MyListResponse.MyListDTO();
//
//        Mockito.when(myListService.findMyListById(sessionUser.getId())).thenReturn(respDTO);
//
//        // when
//        ResultActions actions = mvc.perform(
//                MockMvcRequestBuilders.get("/api/my-favorite-list")
//                        .session(session)
//        );
//
//        // eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("( 응답 ) respBody: " + respBody);
//
//        // then
//        actions.andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
//                .andExpect(MockMvcResultMatchers.view().name("mylist/my-favorite-list"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("MyListDTO"))
//                .andExpect(MockMvcResultMatchers.forwardedUrl("mylist/my-favorite-list"));
//    }
//    @Test
//    public void getMyFavList_test() throws Exception{
//        // given
//        SessionUser sessionUser = new SessionUser();
//        sessionUser.setId(2);
//        session.setAttribute("sessionUser", sessionUser);
//
//        // when
////        ResultActions actions = mvc.perform(
////                MockMvcRequestBuilders.get("/api/my-favorite-list")
////                        .session(session)
////        );
//        MvcResult result = mvc.perform(get("/api/my-favorite-list")
//                .session(session))
//                .andExpect(status().isOk())
//                .andReturn();
//
////        String respBody = actions.andReturn().getResponse().getContentAsString();
////        System.out.println("( 응답 ) respBody: " + respBody);
//
//
//        // then
//        HttpServletRequest request = result.getRequest();
//        MyListResponse.MyListDTO mylistDTO = (MyListResponse.MyListDTO) request.getAttribute("mylistDTO");
//
//        assertThat(mylistDTO).isNotNull();
////        actions.andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(MockMvcResultMatchers.view().name("mylist/my-favorite-list"))
////                .andExpect(MockMvcResultMatchers.model().attributeExists("MyListDTO"))
////                .andExpect(MockMvcResultMatchers.forwardedUrl("mylist/my-favorite-list"));
//    }

}
