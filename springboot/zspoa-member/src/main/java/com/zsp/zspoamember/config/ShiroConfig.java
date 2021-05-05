package com.zsp.zspoamember.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
//    ShiroFiLterFactoryBean:3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")  DefaultWebSecurityManager defaultWebSecurityManager ){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
//        anon:无需认证就可以访问
//        authc:必须认正了才能让问
//        user:必须拥有记住我功能才能用
//        perms:拥有对某个资源的权限才能访问;
//        role:拥有某个角色权限才能访问
//        拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        bean.setLoginUrl("/toLogin");
        return bean;
    }
//    DefauLtwebSecurityManager:2
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);

        return  manager;
    }
//    创建realm对象，需要自定义类:1
    @Bean
    public UserRealm userRealm(){ return new UserRealm();}
}
