package com.lgrochal.socialapp.interfaces.dto;

import com.lgrochal.socialapp.model.Post;

import java.sql.Date;

public class PostDTO {

    private String content;
    private Date createdDate;
    private Long id;

    public PostDTO(){}

    public PostDTO(Post post){
        this.content = post.getContent();
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

}
