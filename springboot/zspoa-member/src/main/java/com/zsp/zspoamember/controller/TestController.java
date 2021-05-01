package com.zsp.zspoamember.controller;

import com.alibaba.fastjson.JSON;
import com.zsp.utils.R;
import com.zsp.zspoamember.entity.Member;
import com.zsp.zspoamember.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
@Autowired
    MemberRepository memberRepository;
    @GetMapping("/add/{userName}/{userPassword}")
    public R addPojo(@PathVariable(value = "userName") String userName,
                     @PathVariable(value = "userPassword") String userPassword){
        Member member = new Member();
        member.setUserName(userName);
        member.setUserPassword(userPassword);
        return R.ok().put("addMember",JSON.toJSONString(memberRepository.save(member)));
    }
}
