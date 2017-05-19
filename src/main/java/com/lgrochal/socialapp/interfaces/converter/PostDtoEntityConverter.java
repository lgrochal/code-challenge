package com.lgrochal.socialapp.interfaces.converter;

import com.lgrochal.socialapp.interfaces.dto.PostDTO;
import com.lgrochal.socialapp.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoEntityConverter implements DtoEntityConverter<Post, PostDTO> {

    @Override
    public PostDTO convertEntityToDto(Post entity) {
        return new PostDTO(entity);
    }

    @Override
    public Post converDtoToEntity(PostDTO dto) {
        return new Post(dto);
    }

    @Override
    public List<Post> convertDtosToEntityList(List<PostDTO> dtos) {
        return dtos.stream().map(e -> converDtoToEntity(e)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> convertEntitiesToDtoList(List<Post> entities) {
        return entities.stream().map(e -> convertEntityToDto(e)).collect(Collectors.toList());
    }
}
