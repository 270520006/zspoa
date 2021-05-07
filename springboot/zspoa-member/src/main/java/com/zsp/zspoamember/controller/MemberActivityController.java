package com.zsp.zspoamember.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsp.utils.R;
import com.zsp.zspoamember.entity.Member;
import com.zsp.zspoamember.entity.MemberActivity;
import com.zsp.zspoamember.service.MemberActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsp
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/member/activity")
public class MemberActivityController {
    @Autowired
    MemberActivityService memberActivityService;
    @GetMapping("/select/{userId}")
    public R selectByUserId(@PathVariable(value = "userId") Long userId){
        QueryWrapper<MemberActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        MemberActivity user = memberActivityService.getOne(queryWrapper);
        return R.ok().put("userActivity", JSON.toJSONString(user));
    }
    @PostMapping("/insert")
    public R insertUser(Long userId,Long activityId,
                        String activityStatus,int activityFinished){
        MemberActivity memberActivity = new MemberActivity();
        memberActivity.setUserId(userId);
        memberActivity.setActivityId(activityId);
        memberActivity.setActivityStatus(activityStatus);
        memberActivity.setActivityFinished(activityFinished);
        return R.ok().put("userActivity", JSON.toJSONString( memberActivityService.save(memberActivity)));
    }

}

