package com.cky.blog.service;


import com.cky.blog.entity.User;

public interface UserService {

    User checkUser(String username, String password);
}
