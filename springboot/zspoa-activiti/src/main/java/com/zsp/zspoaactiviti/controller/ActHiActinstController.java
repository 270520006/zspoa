package com.zsp.zspoaactiviti.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActHiActinst;
import com.zsp.zspoaactiviti.service.ActHiActinstService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
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
@RequestMapping("acti/actinst")
public class ActHiActinstController {
    @Autowired
    ActHiActinstService actHiActinstService;
    @PostMapping("/queryActinst")
    public R createDeployment(@RequestParam("proID") String proID, HttpSession session){

        System.out.println(session.getAttribute("user"));
        try {
            QueryWrapper<ActHiActinst> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PROC_INST_ID_",proID);
            List<ActHiActinst> list = actHiActinstService.list(queryWrapper);
            List<ActHiActinst> actHiActinstList = list.stream()
                    .sorted(Comparator.comparing(ActHiActinst::getStartTime))
                    .collect(Collectors.toList());
            System.out.println(actHiActinstList);
            return R.ok().put("createDeploymentMsg",actHiActinstList);

        } catch (Exception e) {
            return R.error().put("createDeploymentMsg","查询流程失败！");
        }

    }
}

