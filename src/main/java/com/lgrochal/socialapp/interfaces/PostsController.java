package com.lgrochal.socialapp.interfaces;

import java.util.List;

import com.lgrochal.socialapp.interfaces.converter.DtoEntityConverter;
import com.lgrochal.socialapp.interfaces.dto.PostDTO;
import com.lgrochal.socialapp.model.Post;
import com.lgrochal.socialapp.service.PostService;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/{nickname}/posts")
public class PostsController {

    @Autowired
    private PostService postService;

    @Autowired
    @Qualifier("postDtoEntityConverter")
    private DtoEntityConverter<Post, PostDTO> postDtoEntityConverter;

    @RequestMapping(method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PostDTO> presentUserWall(@PathVariable(value="nickname") String nickname) throws ServiceException {
           return postDtoEntityConverter.convertEntitiesToDtoList(postService.getUserPosts(nickname));
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PostDTO createPost(@PathVariable(value="nickname") String nickname, @RequestBody PostDTO postDTO) throws ServiceException {
            // TODO improve user dto convertion and post creation
            return  postDtoEntityConverter.convertEntityToDto(
                    postService.createPost(postDtoEntityConverter.converDtoToEntity(postDTO), nickname)
            );
    }




}
