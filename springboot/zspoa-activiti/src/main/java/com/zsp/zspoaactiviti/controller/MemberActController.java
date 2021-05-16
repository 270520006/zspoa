package com.zsp.zspoaactiviti.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.zsp.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用户端口，用于用户创建流程使用
 */
@RestController
@RequestMapping("acti/task")
public class MemberActController {
    @PostMapping("/isMember")
    public R isMember(String userName,String userPassword){
        if (userName.equals("root")&&userPassword.equals("123456"))
        {
            return R.ok().put("status","ok");
        }

        return R.ok().put("status","no");
    }

    @GetMapping("/createTask")
    public R creatActTask(HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        return R.ok().put("userId",userId);
    }
    @GetMapping("/test")
    public R getSession(HttpSession session){
        return R.ok().put("member",session.getAttribute("Member"));
    }
}
