package com.lgrochal.socialapp.service;

import com.lgrochal.socialapp.model.FollowEvent;
import com.lgrochal.socialapp.model.Post;
import com.lgrochal.socialapp.model.User;
import com.lgrochal.socialapp.repository.FollowEventRepository;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowingService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @Autowired
    private FollowEventRepository followEventRepository;

    public FollowEvent follow(FollowEvent followEvent) throws ServiceException{
        return followEventRepository.save(followEvent);
    }

    //TODO move this responsibility to PostService
    public List<Post> getUserTimeline(String nickname) throws ServiceException {
        userService.validateUser(nickname);
        User user = userService.getUserByNickname(nickname);
        List<Long> followings = followEventRepository.findAllByFollowerId(user.getId()).stream()
                .map(f -> f.getFollowing().getId())
                .collect(Collectors.toList());
        return postService.getAllPostsByFollowings(followings);
    }

}
