package com.zsp.zspoaactiviti.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.utils.R;
import com.zsp.zspoaactiviti.entity.ActRuTask;
import com.zsp.zspoaactiviti.service.ActRuTaskService;
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
 * @since 2021-05-01
 */

@RestController
@RequestMapping("acti/task")
public class ActRuTaskController {
    @Autowired
    ActRuTaskService actRuTaskService;
    @GetMapping("/list")
    public R getTask(){

        return R.ok().put("taskList",JSON.toJSONString(actRuTaskService.list()));
    }
    
}

