package com.example.springbottshiro.config;

import com.example.springbottshiro.pojo.User;
import com.example.springbottshiro.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.util.Password;

/**
 * @author YZY
 * @date 2020/5/9 - 16:19
 */
public class realm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;

//    授权，用于授权账号
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//      只要经过这里就会授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        为每个用户授予该权限
//        info.addStringPermission("user:add");

//        获取当前登录的用户
        Subject subject = SecurityUtils.getSubject();
//        从下面的密码认证第一个 user拿到  user对象
        User user = (User)subject.getPrincipal();
//        为subject设置数据库user对象里的权限
        info.addStringPermission(user.getPerms());

        return info;
    }

//    认证，用于账号验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        UsernamePasswordToken usertoken =  (UsernamePasswordToken)token;

//      从数据库获取数据
        User user = userService.findUserByUsername(usertoken.getUsername());



//        账号认证  ，认证参数里的token是全局存在的，登陆那边封装好了，这边就可以用
        if(user==null){
            return null;
        }
//        我们不做密码认证，有可能泄漏    。shiro暗地里做密码认证，
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
