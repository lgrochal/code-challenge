package com.lgrochal.socialapp.interfaces.converter;

import com.lgrochal.socialapp.interfaces.dto.FollowEventDTO;
import com.lgrochal.socialapp.model.FollowEvent;
import com.lgrochal.socialapp.model.User;
import com.lgrochal.socialapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowEventDtoEntityConverter implements DtoEntityConverter<FollowEvent, FollowEventDTO > {

    @Autowired
    private UserRepository userRepository;

    @Override
    public FollowEventDTO convertEntityToDto(FollowEvent entity) {
        return new FollowEventDTO(entity.getFollower().getNickname(), entity.getFollowing().getNickname());
    }

    @Override
    public FollowEvent converDtoToEntity(FollowEventDTO dto) {
        return new FollowEvent().follower(userRepository.findOneByNickname(dto.getFollowerNickname()))
                .follow(userRepository.findOneByNickname(dto.getFollowingNickname()));
    }

    @Override
    public List<FollowEvent> convertDtosToEntityList(List<FollowEventDTO> dtos) {
        return null;
    }

    @Override
    public List<FollowEventDTO> convertEntitiesToDtoList(List<FollowEvent> entities) {
        return null;
    }
}
