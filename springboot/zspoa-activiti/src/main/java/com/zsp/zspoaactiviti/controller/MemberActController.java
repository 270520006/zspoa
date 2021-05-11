package com.zsp.zspoaactiviti.controller;

import com.zsp.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 用户端口，用于用户创建流程使用
 */
@RestController
@RequestMapping("acti/task")
public class MemberActController {

    @GetMapping("/createTask")
    public R creatActTask(HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        return R.ok().put("userId",userId);
    }

}
