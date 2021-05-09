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
    @GetMapping("/list")
    public R getAllMember(){
        return R.ok().put("members", JSON.toJSONString(memberService.list()));
    }
    @GetMapping("/add/{userName}/{userPassword}")
    public R addMember(@PathVariable String userName, @PathVariable String userPassword ){
        Member member = new Member();
        member.setUserName(userName);
        member.setUserPassword(userPassword);
        return R.ok().put("addResult",JSON.toJSON(memberService.save(member)));
    }

    @GetMapping("/login/{userName}/{userPassword}")
    public R login(@PathVariable(value = "userName") String userName,
                        @PathVariable(value = "userPassword")String userPassword,
                        Model model, HttpSession session){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        Member user = memberService.getOne(queryWrapper);
        try {
            subject.login(token);//执行登陆的方法
            return R.ok().put("login",user.getId());
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return R.error(404,"用户名错误");
        }catch (IncorrectCredentialsException e)
        {
            model.addAttribute("msg","密码错误");
            return R.error(404,"用户名密码错误");
        }
    }

}

