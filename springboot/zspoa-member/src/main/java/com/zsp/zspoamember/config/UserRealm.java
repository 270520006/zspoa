package com.zsp.zspoamember.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.zspoamember.entity.Member;
import com.zsp.zspoamember.service.MemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    MemberService memberService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权操作");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证操作===");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String userName =userToken.getUsername();
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        Member member = memberService.getOne(queryWrapper);
        if (member==null)
        {
            return null;
        }
//            密码认证，shiro做
            return new SimpleAuthenticationInfo("",member.getUserPassword(),"");
    }
}
