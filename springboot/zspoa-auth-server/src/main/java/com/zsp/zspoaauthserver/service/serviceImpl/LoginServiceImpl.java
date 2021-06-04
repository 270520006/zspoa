package com.zsp.zspoaauthserver.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.zsp.entity.User;
import com.zsp.utils.JwtUtils;
import com.zsp.utils.R;
import com.zsp.zspoaauthserver.entity.UserRegist;
import com.zsp.zspoaauthserver.feign.MemberFeignService;
import com.zsp.zspoaauthserver.service.LoginService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    MemberFeignService memberFeignService;
    @Override
    public R userLogin(@Valid User user, BindingResult result, HttpServletResponse response, HttpSession session) {
        System.out.println(user);

        if (result.hasErrors())
        {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            System.out.println(errors);

            return R.error().put("registMsg", JSON.toJSON(errors));
        }
        String userToken = JwtUtils.generateJsonWebToken(user);
        response.setHeader("userToken",userToken);
        session.setAttribute("userToken",userToken);
        System.out.println("加密后的token是："+userToken);
        return R.ok().put("registMsg","登陆成功");
    }

    @Override
    public R userRegist(@Valid UserRegist userRegist, BindingResult result) {
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

    @Override
    public R userCheck(String userToken, HttpSession session) {
        if ( userToken.isEmpty())
        {
            return R.error().put("userToken","用户信息为空");
        }
        if (!session.getAttribute("userToken").equals(userToken)){
            return R.error().put("userToken","你的秘钥经过了修改");
        }
        Claims claims = JwtUtils.checkJWT(userToken);
        System.out.println(claims);
        return R.ok().put("userToken",claims);
    }
}
