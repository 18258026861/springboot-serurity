package com.example.springbottshiro.comtrolller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YZY
 * @date 2020/5/9 - 15:11
 */
@Controller
public class mycomtroller  {


    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("msg","hello");
        return "index";
    }

    @RequestMapping("/toadd")
    public String toadd(){
        return "user/add";
    }

    @RequestMapping("/toupdate")
    public String toupdate(){
        return "user/update";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "user/login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //        获取当前用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
//      执行登录流程（所有验证的步骤shiro都帮我们做了）,如果错误就会报异常
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户不存在");
            return "/user/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "/user/login";
        }
    }

    @RequestMapping("/tounauthorized")
    @ResponseBody
    public String unauthorized(){
        return "未经授权，无法访问";
    }
}
