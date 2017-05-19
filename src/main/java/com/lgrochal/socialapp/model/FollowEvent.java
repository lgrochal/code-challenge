package com.lgrochal.socialapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class FollowEvent {

    @Id
    @GeneratedValue
    private Long id;
    //TODO improve mapprings

    @ManyToOne
    private User follower;

    @ManyToOne
    private User following;

    private Timestamp createdDate;

    @PrePersist
    public void setCreatedDate(){
        this.createdDate = new Timestamp(new java.util.Date().getTime());
    }

    public FollowEvent(){}


    public FollowEvent follower(User follower){
        this.follower = follower;
        return this;
    }

    public FollowEvent follow(User following){
        this.following = following;
        return this;
    }

    public User getFollower(){
        return this.follower;
    }


    public User getFollowing(){
        return this.following;
    }

}
