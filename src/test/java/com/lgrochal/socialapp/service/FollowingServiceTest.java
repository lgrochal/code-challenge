package com.lgrochal.socialapp.service;

import com.lgrochal.socialapp.model.FollowEvent;
import com.lgrochal.socialapp.model.Post;
import com.lgrochal.socialapp.model.User;
import com.lgrochal.socialapp.repository.FollowEventRepository;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FollowingServiceTest extends AbstractServiceTest {

    @InjectMocks
    private FollowingService followingService;

    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    @Mock
    private FollowEventRepository followEventRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_follow_user() throws ServiceException {
        //given
        User follower = createUser();
        User followed = createUser();
        FollowEvent followEvent = new FollowEvent().follower(follower).follow(followed);

        //when
        followingService.follow(followEvent);
    }


    @Test
    public void should_show_user_timeline() throws ServiceException {
        //given
        User follower = createUser();
        User following = createUser();
        Post p = new Post();
        p.setUser(new User("abc"));

        List<FollowEvent> followEvents = Arrays.asList(new FollowEvent().follower(follower).follow(following));
        List<Post> posts = Arrays.asList(p);
        doAnswer(i -> {
            return null;
        }).when(userService).validateUser(follower.getNickname());
        when(userService.getUserByNickname(follower.getNickname())).thenReturn(follower);
        when(followEventRepository.findAllByFollowerId(follower.getId())).thenReturn(followEvents);
        List<Long> followings = followEvents.stream().map( f -> f.getFollowing().getId()).collect(Collectors.toList());
        when(postService.getAllPostsByFollowings(followings))
                .thenReturn(posts);

        //when
        followingService.getUserTimeline(follower.getNickname());

        //then
        verify(userService).validateUser(follower.getNickname());
        verify(followEventRepository).findAllByFollowerId(follower.getId());
        verify(postService).getAllPostsByFollowings(followings);
    }

}
