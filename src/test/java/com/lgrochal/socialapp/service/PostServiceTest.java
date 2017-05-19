package com.lgrochal.socialapp.service;

import com.lgrochal.socialapp.model.Post;
import com.lgrochal.socialapp.model.User;
import com.lgrochal.socialapp.repository.PostRepository;
import com.lgrochal.socialapp.repository.UserRepository;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest extends AbstractServiceTest {

    @InjectMocks
    private PostService postService;

    @Spy @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(userRepository.save(any(User.class))).then((invocationOnMock -> {
            User user = (User) invocationOnMock.getArguments()[0];
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
            return user;
        }));
        when(postRepository.save(any(Post.class))).then((invocationOnMock -> {
            Post post = (Post) invocationOnMock.getArguments()[0];
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Post>> violations = validator.validate(post);
            if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
            return post;
        }));
    }

    @Test
    public void should_create_post() throws ServiceException {
        Post post = createPost();
        User user = createUser();
        post.setUser(user);

        postService.createPost(post, user.getNickname());

        verify(postRepository, times(1)).save(post);
        verify(userService, times(1)).createUserIfNotExists(user.getNickname());
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_not_create_post() throws ServiceException {
        Post post = createPost();

        postService.createPost(post, "nickname#$%");

        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void should_get_user_posts() throws ServiceException {
        Post post = createPost();
        User user = createUser();
        post.setUser(user);

        doAnswer((invocation) -> {
                Object[] args = invocation.getArguments();
                return null;
        }).when(userService).validateUser(user.getNickname());
        when(postRepository.findAllByUserNickname(user.getNickname())).thenReturn(Arrays.asList(post));

        postService.getUserPosts(user.getNickname());

        verify(userService, times(1)).validateUser(post.getUser().getNickname());
        verify(postRepository, times(1)).findAllByUserNickname(post.getUser().getNickname());
    }

    @Test(expected = ServiceException.class)
    public void should_not_get_user_posts() throws ServiceException {
        Post post = createPost();
        User user = createUser();
        post.setUser(user);
        reset(userService);
        doThrow(new ServiceException("User not exists")).when(userService).validateUser(user.getNickname());

        postService.getUserPosts(user.getNickname());

        verify(userService, times(1)).validateUser(post.getUser().getNickname());
    }



}
