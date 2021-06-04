package com.zsp.zspoaauthserver.service;

import com.zsp.entity.User;
import com.zsp.utils.R;
import com.zsp.zspoaauthserver.entity.UserRegist;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


public interface LoginService {
    public R userLogin(@Valid User user, BindingResult result,
                       HttpServletResponse response, HttpSession session);

    public R userRegist(@Valid UserRegist userRegist, BindingResult result);
    public R userCheck(String userToken,HttpSession session);
}
