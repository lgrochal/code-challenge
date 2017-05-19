package com.lgrochal.socialapp.interfaces.dto;

import com.lgrochal.socialapp.model.Post;

import java.util.Date;


public class PostDTO {

    private String content;
    private java.util.Date createdDate;
    private Long id;

    public PostDTO(){}

    public PostDTO(Post post){
        this.content = post.getContent();
        this.createdDate = new Date(post.getCreatedDate().getTime());
        this.id = post.getId();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
