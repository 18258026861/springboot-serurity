package com.example.springbottshiro;

import com.example.springbottshiro.pojo.User;
import com.example.springbottshiro.service.UserService;
import com.example.springbottshiro.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class SpringbottShiroApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User yy = userService.findUserByUsername("YY");
        System.out.println(yy);
    }

}
