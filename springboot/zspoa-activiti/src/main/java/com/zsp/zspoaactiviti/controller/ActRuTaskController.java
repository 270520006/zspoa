package com.zsp.zspoaactiviti.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActReDeployment;
import com.zsp.zspoaactiviti.entity.ActRuTask;
import com.zsp.zspoaactiviti.feign.MemberFeignService;
import com.zsp.zspoaactiviti.service.ActRuTaskService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
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



    @PostMapping("/allowDeployment")
    public R allowDeployment(@RequestParam("instID") String instID,HttpSession session){
        try {
            QueryWrapper<ActRuTask> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PROC_INST_ID_",instID);
            ActRuTask ruTask = actRuTaskService.getOne(queryWrapper);
            System.out.println(ruTask);
            String id = ruTask.getId();
            System.out.println(id);
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//    查看act_ru_task表，然后把id给上，发起请假申请
            processEngine.getTaskService()
                    .complete(id);
        } catch (Exception e) {
            return R.error().put("allowDeploymentMsg","发生错误！");
        }
        return R.ok().put("allowDeploymentMsg","审批流程通过！");
    }
    @GetMapping("/list")
    @Cacheable(value = {"taskList"},key = "#root.methodName")
    public R getTaskList(){
        return R.ok().put("taskList",JSON.toJSONString(actRuTaskService.list()));
    }

    @GetMapping("/member/list")
    @Cacheable(value = {"memberList"},key = "#root.methodName",sync = true)
    public R getMemberList(HttpSession session){
        System.out.println("查询了数据库");

        Object member = session.getAttribute("Member");
        System.out.println(member.toString());
        return R.ok().put("memberList",memberFeignService.memberList()) ;
    }

    

    @GetMapping("/member/add/{userName}/{userPassword}")
    @CacheEvict(value = "memberList",key = "'getMemberList'")
    public R addMember(@PathVariable(value = "userName")String userName,
                       @PathVariable(value = "userPassword")String userPassword){
        return memberFeignService.addMember(userName,userPassword);

    }
    @GetMapping("member/activity/select/{userId}")
    public R selectByUserId(@PathVariable(value = "userId" )Long userId){
        return R.ok().put("user",memberFeignService.selectByUserId(userId)) ;

    }


    
}

