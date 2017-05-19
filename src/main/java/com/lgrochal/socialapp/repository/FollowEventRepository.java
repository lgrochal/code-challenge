package com.lgrochal.socialapp.repository;

import com.lgrochal.socialapp.model.FollowEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowEventRepository  extends CrudRepository<FollowEvent, Long> {

    @Query("select f from FollowEvent f where f.follower.id = :followerId")
    List<FollowEvent> findAllByFollowerId(@Param("followerId") Long followerId);
}
