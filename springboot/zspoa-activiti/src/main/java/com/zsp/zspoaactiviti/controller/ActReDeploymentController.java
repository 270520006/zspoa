package com.zsp.zspoaactiviti.controller;


import com.alibaba.fastjson.JSON;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActReDeployment;
import com.zsp.zspoaactiviti.service.ActReDeploymentService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
@RequestMapping("acti/deployment")
public class ActReDeploymentController {
    @Autowired
    ActReDeploymentService actReDeploymentService;


    @PostMapping("/createDeployment")
    public R createDeployment(@RequestParam("proID") String proID,HttpSession session){

        System.out.println(session.getAttribute("user"));
        try {
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            //act_re_procdef表里的id，如果没生成，去看上一步，真是哔了狗
            String id = processEngine.getRuntimeService()
                    .startProcessInstanceById(proID).getId();
            System.out.println(id);

        } catch (Exception e) {
            return R.error().put("createDeploymentMsg","创建流程失败！");
        }
        return R.ok().put("createDeploymentMsg","创建流程成功！");
    }
    @PostMapping("/isMember")
    public R isMember(String userName,String userPassword){
        System.out.println(userName+"===="+userPassword);
        if (userName.equals("root")&&userPassword.equals("123456"))
        {
            return R.ok().put("status","ok");
        }

        return R.ok().put("status","no");
    }

    @GetMapping("/testMember/{userName}/{userPassword}")
    public R testMember(@PathVariable(value = "userName") String userName,
                        @PathVariable(value = "userPassword")String userPassword){
        System.out.println(userName+"===="+userPassword);
        if (userName.equals("root")&&userPassword.equals("123456"))
        {
            return R.ok().put("status","ok");
        }

        return R.ok().put("status","no");
    }

    /**
     * #root.methodName表示使用方法名作为分组名
     * @return
     */
    @GetMapping("/list")
    @Cacheable(value = {"deploymentList"},key = "#root.methodName")
    public R getDeploymentList(){

        Map<String, ActReDeployment> deploymentMap = actReDeploymentService.list().stream()
                .collect(Collectors.toMap(ActReDeployment::getId, actReDeployment -> actReDeployment));
         return R.ok().put("deploymentList", JSON.toJSONString(deploymentMap));
    }
}

