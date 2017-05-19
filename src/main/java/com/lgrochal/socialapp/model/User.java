package com.lgrochal.socialapp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Pattern(regexp="^[a-zA-Z0-9]*$",
            message="Invalid nickname only alpha-numeric characters allowed")
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User(){}

    //TODO improve follower/following mapping

    public User(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, nickname='%s']",
                id, nickname);
    }


    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post){
        if(!posts.contains(post)) this.posts.add(post);
    }
}
