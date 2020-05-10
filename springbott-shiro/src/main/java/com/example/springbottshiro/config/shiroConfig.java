package com.example.springbottshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author YZY
 * @date 2020/5/9 - 16:16
 */
@Configuration
public class shiroConfig  {

//   shiroFilterFactoryBan                        这是第三部，需要manage管理
    @Bean
    public ShiroFilterFactoryBean bean(@Qualifier("manager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

            /*添加shiro的内置过滤器
            * anon：无需认证
            * authc ： 必须认证才能访问
            * user：必须拥有  记住我  功能才能访问
            * perms: 拥有对某个资源的权限才能访问
            * role： 拥有某个角色权限才能访问
            * */
        HashMap<String,String> map = new HashMap<>();
        /*设置资源权限
        * 这里的资源写的是路径（controller里的），不是页面*/

//             perms[user:add]代表用户登录并且拥有add权限  ，未授权为401，一般会跳转到未授权页面
        map.put("/toadd","perms[user:add]");
        map.put("/toupdate","perms[user:update]");
//          将设置的资源权限放入过滤器
        bean.setFilterChainDefinitionMap(map);

        /*设置页面*/
//        设置跳转登录页面，当被拦截时跳转到登录页面
        bean.setLoginUrl("/tologin");
//        设置未授权请求的页面
        bean.setUnauthorizedUrl("/tounauthorized");

        return bean;
    }

//      DefaultWebSecurityManager               这是第二部，因为manager需要realm
    @Bean(name="manager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("realm") realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//          关联realm  ，使用spring容器参数
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

//    realm  对象，需要自定义（创建一个realm类）   这是第一步
    @Bean
    public realm realm(){
        return new realm();
    }


//   整合shiroDialect： 用来整合shiro 和thymeleaf   这个用于使用shiro自定义tag
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
