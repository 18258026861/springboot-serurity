package com.example.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author YZY
 * @date 2020/5/7 - 17:48
 */
//AOP  ，类似拦截器
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//              首页所有人都可以访问，功能页只有对应的权限得人才能访问
//        请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
//        没有权限显示拒绝访问很难看，所以跳转到登录页
//              定制登录页 loginPage
        http.formLogin().loginPage("/toLogin");

//        关闭防止网站攻击工具
        http.csrf().disable();

//        记住我  使用cookie  默认保存14天
        http.rememberMe().rememberMeParameter("remember");

//      注销,  成功返回首页
        http.logout().logoutSuccessUrl("/");
    }

//    认证    springboot版本2.1.x以上可以能会报错 提示密码编码为null
//    添加密码编码.passwordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        这些数据可以从数据库中读，也可以从内存中读，速度较快
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("YY").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2")
                .and().withUser("YZY").password(new BCryptPasswordEncoder().encode("123456")).roles("vip3");
    }
}
