package com.lgrochal.socialapp.interfaces;

import com.lgrochal.socialapp.interfaces.converter.DtoEntityConverter;
import com.lgrochal.socialapp.interfaces.dto.FollowEventDTO;
import com.lgrochal.socialapp.model.FollowEvent;
import com.lgrochal.socialapp.service.FollowingService;
import com.lgrochal.socialapp.service.PostService;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/follow")
public class FollowingController {

    @Autowired
    private PostService postService;

    @Autowired
    @Qualifier("followEventDtoEntityConverter")
    private DtoEntityConverter<FollowEvent, FollowEventDTO> followEventDtoEntityConverter;

    @Autowired
    private FollowingService followingService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FollowEventDTO followUser(@RequestBody FollowEventDTO followEventDTO) throws ServiceException {
        return followEventDtoEntityConverter.convertEntityToDto(
                followingService.follow(followEventDtoEntityConverter.converDtoToEntity(followEventDTO))
        );
    }
}
