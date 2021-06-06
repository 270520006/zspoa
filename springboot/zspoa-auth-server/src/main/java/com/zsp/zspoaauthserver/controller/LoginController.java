package com.zsp.zspoaauthserver.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsp.entity.User;
import com.zsp.utils.JwtUtils;
import com.zsp.utils.R;
import com.zsp.zspoaauthserver.entity.UserRegist;
import com.zsp.zspoaauthserver.feign.MemberFeignService;
import com.zsp.zspoaauthserver.feign.SearchFeignService;
import com.zsp.zspoaauthserver.service.LoginService;
import com.zsp.zspoaauthserver.service.serviceImpl.LoginServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    SearchFeignService searchFeignService;
    @PostMapping("/login")
    public R userLogin(@Valid User user, BindingResult result,
                       HttpServletResponse response, HttpSession session){
        return loginService.userLogin(user,result,response,session);
    }



    @PostMapping("/regist")
    public R userRegist(@Valid UserRegist userRegist, BindingResult result){
         return loginService.userRegist(userRegist,result);
    }
    @PostMapping("/check")
    public R userCheck(String userToken,HttpSession session){
        return loginService.userCheck(userToken,session);
    }
}
