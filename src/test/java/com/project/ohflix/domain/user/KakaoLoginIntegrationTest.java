package com.project.ohflix.domain.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ohflix.ProjectOhflixApplication;
import com.project.ohflix._core.utils.DummyObject;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(classes = ProjectOhflixApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class KakaoLoginIntegrationTest extends DummyObject {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private EntityManager em;

    @Mock
    private UserService userService;

    private HttpSession session;

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        public RedisTemplate<String, Object> redisTemplate() {
            return org.mockito.Mockito.mock(RedisTemplate.class);
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void kakaoLoginCallback_success_test() throws Exception {
        // given
        User mockAppUser = newUser("mockUser@example.com", "mockUser", "password", "Mock User");

        when(userService.kakaoLogin(anyString())).thenReturn(mockAppUser);

        // when
        MvcResult result = mvc.perform(get("/oauth/kakao/callback")
                        .param("code", "mockAuthCode"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/main-page"))
                .andReturn();

        // then
        session = result.getRequest().getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        assertNotNull(sessionUser);
        assertEquals(mockAppUser.getId(), sessionUser.getId());
    }
}
