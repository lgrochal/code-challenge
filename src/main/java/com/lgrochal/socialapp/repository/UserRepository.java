package com.lgrochal.socialapp.repository;

import com.lgrochal.socialapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByNickname(String nickname);
}