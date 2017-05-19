package com.lgrochal.socialapp.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgrochal.socialapp.SocialApp;
import com.lgrochal.socialapp.interfaces.dto.FollowEventDTO;
import com.lgrochal.socialapp.interfaces.dto.PostDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApp.class)
@WebAppConfiguration
@TestPropertySource(locations="classpath:application-test.properties")
public class TimelineControllerIT extends AbstractControllerIT{

    @EnableWebMvc
    public static class TestConfig {
    }

    private MockMvc mockMvc;

    @Autowired
    private PostsController postsController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init(){
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void should_follow_user_and_see_his_posts() throws Exception {
        //given
        PostDTO post = createPost();
        String follwer = "user";


        mockMvc.perform(post("/"+follwer + "/posts")
                .content(json(post))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        PostDTO post1 = createPost();
        String following = "josh";

        mockMvc.perform(post("/"+following + "/posts")
                .content(json(post1))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        FollowEventDTO followEventDTO = new FollowEventDTO();
        followEventDTO.setFollowerNickname(follwer);
        followEventDTO.setFollowingNickname(following);

        //when
        mockMvc.perform(post("/follow")
                .content(json(followEventDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        mockMvc.perform(get("/" + follwer + "/timeline")
                .content(json(followEventDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(json(Arrays.asList(post1))));
    }
}
