package com.lgrochal.socialapp.service;

import com.lgrochal.socialapp.model.Post;
import com.lgrochal.socialapp.model.User;

import java.util.Random;

public class AbstractServiceTest {
    protected Post createPost(){
        Post post = new Post();
        Random r = new Random();
        post.setContent("post content - " + r.nextInt());
        return post;
    }

    protected User createUser(){
        User user = new User();
        Random r = new Random();
        user.setNickname("nickname" + Math.abs(r.nextInt()));
        user.setId(Math.abs(r.nextLong()));
        return user;
    }
}
