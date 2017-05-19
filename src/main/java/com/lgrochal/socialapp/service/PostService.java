package com.lgrochal.socialapp.service;

import com.lgrochal.socialapp.model.Post;
import com.lgrochal.socialapp.model.User;
import com.lgrochal.socialapp.repository.PostRepository;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    public Post createPost(Post post, String nickname) throws ServiceException {
        User user = userService.createUserIfNotExists(nickname);
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<Post> getUserPosts(String nickname) throws ServiceException {
        userService.validateUser(nickname);

        return postRepository.findAllByUserNickname(nickname);
    }

    public List<Post> getAllPostsByFollowings(List<Long> followings) {
        return postRepository.findAllByUserIds(followings);
    }
}
