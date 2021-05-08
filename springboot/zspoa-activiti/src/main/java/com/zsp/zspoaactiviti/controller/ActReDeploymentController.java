package com.zsp.zspoaactiviti.controller;


import com.alibaba.fastjson.JSON;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActReDeployment;
import com.zsp.zspoaactiviti.service.ActReDeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("acti/deployment")
public class ActReDeploymentController {
    @Autowired
    ActReDeploymentService actReDeploymentService;

    /**
     * #root.methodName表示使用方法名作为分组名
     * @return
     */
    @GetMapping("/list")
    @Cacheable({"deploymentList"})
    public R deploymentList(){

        Map<String, ActReDeployment> deploymentMap = actReDeploymentService.list().stream()
                .collect(Collectors.toMap(ActReDeployment::getId, actReDeployment -> actReDeployment));

        return R.ok().put("deploymentList", JSON.toJSONString(deploymentMap));

    }
}

