package com.lgrochal.socialapp.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgrochal.socialapp.SocialApp;
import com.lgrochal.socialapp.interfaces.dto.PostDTO;
import com.lgrochal.socialapp.interfaces.exception.GlobalExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApp.class)
@WebAppConfiguration
@TestPropertySource(locations="classpath:application-test.properties")
public class PostsControllerIT extends AbstractControllerIT{

    @EnableWebMvc
    public static class TestConfig {
    }

    private MockMvc mockMvc;

    @Autowired
    private PostsController postsController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init(){
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void should_create_post_test() throws Exception {
        //given
        PostDTO post = createPost();
        String userNickname = "nickname432";

        //when
        mockMvc.perform(post("/"+userNickname + "/posts")
                        .content(json(post))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
        //then
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void should_not_create_post_because_constraint_test() throws Exception {
        //given
        PostDTO post = createPost();

        //when
        mockMvc.perform(post("/123$%/posts")
                .content(json(post))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //then
                .andExpect(status().is(400));
    }

    @Test
    public void should_get_posts_test() throws Exception {
        //given
        PostDTO post = createPost();
        String userNickname = "nickname123";

        //when
        mockMvc.perform(post("/"+userNickname + "/posts")
                .content(json(post))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //then
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(get("/"+userNickname + "/posts"))
                //then
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(json(Arrays.asList(post))));

    }

    @Test
    public void should_not_get_posts_test() throws Exception {
        //given
        PostDTO post = createPost();
        String userNickname = "nickname123123";

        //when
        mockMvc.perform(get("/"+userNickname + "/posts"))
                //then
                .andExpect(status().is(404));

    }


}
