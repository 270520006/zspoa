package com.zsp.zspoaactiviti.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActRuTask;
import com.zsp.zspoaactiviti.feign.MemberFeignService;
import com.zsp.zspoaactiviti.service.ActRuTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsp
 * @since 2021-05-01
 */

@RestController
@RequestMapping("acti/task")
public class ActRuTaskController {
    @Autowired
    ActRuTaskService actRuTaskService;
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/list")
    public R getTaskListJson(){
        String taskList = stringRedisTemplate.opsForValue().get("taskList");
        System.out.println("查找的结果"+taskList);
        if (StringUtils.isEmpty(taskList))
        {
            System.out.println("进入了方法");
            String str =JSON.toJSONString(getTaskListFromDB());
            System.out.println("查找了数据库");
            stringRedisTemplate.opsForValue().set("taskList", str);
            System.out.println(str);
            return   R.ok().put("taskList",str);
        }
        return R.ok().put("taskList",taskList);

    }
    public String getTaskListFromDB(){
        System.out.println("通过数据库查询了流程任务表");
        return JSON.toJSONString(actRuTaskService.list());
    }

    @GetMapping("/member/list")
    public R getMemberList(){
        return memberFeignService.memberList();
    }
    
}

