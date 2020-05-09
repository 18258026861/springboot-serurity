package com.example.springbottshiro.service;

import com.example.springbottshiro.pojo.User;

/**
 * @author YZY
 * @date 2020/5/9 - 20:47
 */
public interface UserService {

    public User findUserByUsername(String username);

}
