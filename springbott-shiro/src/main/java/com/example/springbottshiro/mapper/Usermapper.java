package com.example.springbottshiro.mapper;

import com.example.springbottshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author YZY
 * @date 2020/5/9 - 20:30
 */
@Mapper
@Repository
public interface Usermapper {

    User findUserByUsername(String username);

}
