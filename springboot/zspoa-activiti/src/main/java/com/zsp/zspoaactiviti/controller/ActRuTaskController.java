package com.zsp.zspoaactiviti.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActRuTask;
import com.zsp.zspoaactiviti.feign.MemberFeignService;
import com.zsp.zspoaactiviti.service.ActRuTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<String, ActRuTask> getTaskListFromDB(){
        System.out.println("通过数据库查询了流程任务表");
        Map<String, ActRuTask> taskList = actRuTaskService.list()
                                            .stream()
                .collect(Collectors.toMap(ActRuTask::getId, actRuTask -> actRuTask));
        return  taskList;
    }

    @GetMapping("/member/list")
    @Cacheable(value = "memberList")
    public R getMemberList(){
        return memberFeignService.memberList();
    }

    @GetMapping("/member/add/{userName}/{userPassword}")
    @CachePut(value = "memberList")
    public R addMember(@PathVariable(value = "userName")String userName,
                       @PathVariable(value = "userPassword")String userPassword){
        return memberFeignService.addMember(userName,userPassword);
    }
    @GetMapping("member/activity/select/{userId}")
    public R selectByUserId(@PathVariable(value = "userId" )Long userId){
        return memberFeignService.selectByUserId(userId);
    }


    
}

