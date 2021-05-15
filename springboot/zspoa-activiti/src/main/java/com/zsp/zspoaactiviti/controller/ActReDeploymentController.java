package com.zsp.zspoaactiviti.controller;


import com.alibaba.fastjson.JSON;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActReDeployment;
import com.zsp.zspoaactiviti.service.ActReDeploymentService;
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
    public R deploymentList(){

        Map<String, ActReDeployment> deploymentMap = actReDeploymentService.list().stream()
                .collect(Collectors.toMap(ActReDeployment::getId, actReDeployment -> actReDeployment));
         return R.ok().put("deploymentList", JSON.toJSONString(deploymentMap));

    }
}

