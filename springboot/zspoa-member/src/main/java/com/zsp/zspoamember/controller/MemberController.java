package com.zsp.zspoamember.controller;


import com.alibaba.fastjson.JSON;
import com.zsp.utils.R;
import com.zsp.zspoamember.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}
