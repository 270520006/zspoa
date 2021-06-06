package com.zsp.zspoamember.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.utils.R;
import com.zsp.zspoamember.entity.Member;
import com.zsp.zspoamember.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsp
 * @since 2021-05-03
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;
    @PostMapping("/regist")
    public R userRegist(String userName,String password,String userPhone){
        System.out.println(userName+"电话是："+userPhone);
        QueryWrapper queryUserName = new QueryWrapper();
        QueryWrapper queryUserPhone=new QueryWrapper();
        queryUserName.eq("user_name",userName);
        queryUserPhone.eq("user_phone",Long.parseLong(userPhone));
        Member memberName = memberService.getOne(queryUserName);
        Member memberPhone = memberService.getOne(queryUserPhone);
        if (memberName!=null){
            return R.error().put("registMsg","此账户已被注册");
        }
        if (memberPhone!=null)
        {
            return R.error().put("registMsg","电话号码已被注册");

        }
        try {
            Member member = new Member();
            member.setUserName(userName);
            member.setUserPhone(Long.parseLong(userPhone));
            member.setUserPassword(password);
            memberService.save(member);
            return R.ok().put("registMsg","注册成功！");
        } catch (NumberFormatException e) {
            return R.error().put("registMsg","存入数据库失败！");
        }
    }
    @GetMapping("/list")
    public R getAllMember(){
        return R.ok().put("memberList", JSON.toJSON(memberService.list()));
    }
    @GetMapping("/add/{userName}/{userPassword}")
    public R addMember(@PathVariable String userName, @PathVariable String userPassword ){
        Member member = new Member();
        member.setUserName(userName);
        member.setUserPassword(userPassword);
        return R.ok().put("addResult",JSON.toJSON(memberService.save(member)));
    }

    @GetMapping("/login/{userName}/{userPassword}")
    public String login(@PathVariable(value = "userName") String userName,
                        @PathVariable(value = "userPassword")String userPassword,
                        Model model, HttpSession session){


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);

        Member member = memberService.getOne(queryWrapper);
        session.setAttribute("Member",member.toString());
         return member.toString();
    }

}

