package com.lgrochal.socialapp.service;

import com.lgrochal.socialapp.model.Post;
import com.lgrochal.socialapp.model.User;
import com.lgrochal.socialapp.repository.UserRepository;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends AbstractServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_user() throws ServiceException {
        String nickname = "nickname1223";
        when(userRepository.findOneByNickname(nickname)).thenReturn(null);
        when(userRepository.save(any(User.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);
        User user = userService.createUserIfNotExists(nickname);

        verify(userRepository, times(1)).save(user);
    }


    @Test
    public void should_not_create_user() throws ServiceException {
        String nickname = "nickname1223";

        when(userRepository.findOneByNickname(nickname)).thenReturn(new User(nickname));

        User user1 = userService.createUserIfNotExists(nickname);

        verify(userRepository, times(1)).findOneByNickname(nickname);
    }
}
