package com.lgrochal.socialapp.repository;

import com.lgrochal.socialapp.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("select p from Post p where p.user.nickname = :nickname order by createdDate desc")
    List<Post> findAllByUserNickname(@Param("nickname") String nickname);

    @Query("select p from Post p where p.user.id in :ids order by createdDate desc")
    List<Post> findAllByUserIds(@Param("ids") List<Long> ids);
}

