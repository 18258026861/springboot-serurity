package com.example.springbottshiro.service;

import com.example.springbottshiro.mapper.Usermapper;
import com.example.springbottshiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author YZY
 * @date 2020/5/9 - 20:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Usermapper usermapper;

    @Override
    public User findUserByUsername(String username) {
        return usermapper.findUserByUsername(username);
    }
}
