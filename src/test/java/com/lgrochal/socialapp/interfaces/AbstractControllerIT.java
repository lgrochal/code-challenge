package com.lgrochal.socialapp.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgrochal.socialapp.interfaces.dto.PostDTO;

import java.util.Random;

public class AbstractControllerIT {

    private ObjectMapper mapper = new ObjectMapper();

    protected PostDTO createPost(){
        PostDTO post = new PostDTO();
        Random r = new Random();
        post.setContent("post content - " + r.nextInt());
        return post;
    }

    protected String json(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
