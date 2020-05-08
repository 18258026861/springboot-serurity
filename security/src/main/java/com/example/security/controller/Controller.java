package com.example.security.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YZY
 * @date 2020/5/7 - 16:21
 */
@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String tologin(){
        return "views/login";
    }

//    秒啊，根据id跳转对应的网页
    @RequestMapping("/level1/{id}")
    public String leavel1(@PathVariable("id") int id){
        return "views/level1/"+id;
    }

    @RequestMapping("/level2/{id}")
    public String leavel2(@PathVariable("id") int id){
        return "views/level2/"+id;
    }

    @RequestMapping("/level3/{id}")
    public String leavel3(@PathVariable("id") int id){
        return "views/level3/"+id;
    }

}
