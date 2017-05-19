package com.lgrochal.socialapp.interfaces.converter;

import java.util.List;

public interface DtoEntityConverter<E, D> {
    D convertEntityToDto(E entity);
    E converDtoToEntity(D dto);
    List<E> convertDtosToEntityList(List<D> dtos);
    List<D> convertEntitiesToDtoList(List<E> entities);

}
