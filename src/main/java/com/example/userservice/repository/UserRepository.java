package com.example.userservice.repository;

import com.example.userservice.vo.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
