package com.zsp.zspoaauthserver.controller;


import com.alibaba.fastjson.JSON;
import com.zsp.utils.R;
import com.zsp.zspoaauthserver.entity.UserRegist;
import com.zsp.zspoaauthserver.feign.MemberFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    MemberFeignService memberFeignService;
    @PostMapping("/regist")
    public R userRegist(@Valid UserRegist userRegist, BindingResult result){
        System.out.println(userRegist);
        if (result.hasErrors())
        {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            System.out.println(errors);

            return R.error().put("registMsg", JSON.toJSON(errors));
        }
//            return R.ok().put("registMsg","注册成功！");
        return memberFeignService.memberRegist(userRegist.getUsername(),userRegist.getPhone());
    }
}
