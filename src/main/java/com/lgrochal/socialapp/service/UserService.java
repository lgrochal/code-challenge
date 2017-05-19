package com.lgrochal.socialapp.service;

import com.lgrochal.socialapp.service.exception.ResourceNotFoundException;
import com.lgrochal.socialapp.model.User;
import com.lgrochal.socialapp.repository.UserRepository;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private Object userByNickname;

    public User createUserIfNotExists(String nickname) throws ServiceException{
        if(nickname == null){
            throw new ServiceException("Nickname cannot be null");
        }
        User user = new User(nickname);
        User userLookup = userRepository.findOneByNickname(user.getNickname());

        if(userLookup == null) {
            return userRepository.save(user);
        }
        return userLookup;
    }

    public void validateUser(String nickname) throws ServiceException{
        if(userRepository.findOneByNickname(nickname) == null){
            throw new ResourceNotFoundException("User " + nickname + " does not exists");
        }
    }

    public User getUserByNickname(String nickname) {
        return userRepository.findOneByNickname(nickname);
    }
}
