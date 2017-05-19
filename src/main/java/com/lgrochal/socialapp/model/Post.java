package com.lgrochal.socialapp.model;

import com.lgrochal.socialapp.interfaces.dto.PostDTO;
import javafx.geometry.Pos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Size(max=140)
    @NotNull
    private String content;

    private Date createdDate;

    @ManyToOne
    @JoinColumn
    @NotNull
    private User user;

    public Post(){
        this.createdDate = new Date(new java.util.Date().getTime());
    }

    public Post(PostDTO postDTO){
        this.id = postDTO.getId();
        this.content = postDTO.getContent();
        this.createdDate = postDTO.getCreatedDate();
    }


    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
