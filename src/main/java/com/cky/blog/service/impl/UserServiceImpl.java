package com.cky.blog.service.impl;

import com.cky.blog.entity.User;
import com.cky.blog.mapper.UserRepository;
import com.cky.blog.service.UserService;
import com.cky.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
