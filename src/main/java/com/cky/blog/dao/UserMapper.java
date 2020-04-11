package com.cky.blog.dao;

import com.cky.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);
}
