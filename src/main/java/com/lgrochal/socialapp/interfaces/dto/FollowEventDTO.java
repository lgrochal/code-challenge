package com.lgrochal.socialapp.interfaces.dto;

import com.lgrochal.socialapp.model.FollowEvent;

public class FollowEventDTO {
    private String followerNickname;
    private String followingNickname;

    public FollowEventDTO(){}

    public FollowEventDTO(String followerNickname, String followingNickname) {
        this.followingNickname = followerNickname;
        this.followerNickname = followingNickname;
    }

    public String getFollowerNickname() {
        return followerNickname;
    }

    public void setFollowerNickname(String followerNickname) {
        this.followerNickname = followerNickname;
    }

    public String getFollowingNickname() {
        return followingNickname;
    }

    public void setFollowingNickname(String followingNickname) {
        this.followingNickname = followingNickname;
    }
}
