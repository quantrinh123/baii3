package com.quan.bai3.repository;

import com.quan.bai3.entity.UserEntity;

import java.util.Optional;

public interface UserRepository{
    Optional<UserEntity> findByUsername(String username);
}
