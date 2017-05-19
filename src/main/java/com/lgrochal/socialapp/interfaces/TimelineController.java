package com.lgrochal.socialapp.interfaces;

import com.lgrochal.socialapp.interfaces.converter.DtoEntityConverter;
import com.lgrochal.socialapp.interfaces.dto.PostDTO;
import com.lgrochal.socialapp.service.FollowingService;
import com.lgrochal.socialapp.service.PostService;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/{nickname}/timeline")
public class TimelineController {

    @Autowired
    private FollowingService followingService;

    @Autowired
    @Qualifier("postDtoEntityConverter")
    private DtoEntityConverter postDtoEntityConverter;

    @RequestMapping(method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<PostDTO> presentUserTimeline(@PathVariable(value="nickname") String nickname) throws ServiceException {
        return postDtoEntityConverter.convertEntitiesToDtoList(followingService.getUserTimeline(nickname));
    }
}
