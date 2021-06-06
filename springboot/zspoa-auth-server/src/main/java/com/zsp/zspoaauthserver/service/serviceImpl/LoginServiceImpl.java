package com.zsp.zspoaauthserver.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zsp.entity.User;
import com.zsp.utils.JwtUtils;
import com.zsp.utils.R;
import com.zsp.zspoaauthserver.entity.Member;
import com.zsp.zspoaauthserver.entity.UserRegist;
import com.zsp.zspoaauthserver.feign.MemberFeignService;
import com.zsp.zspoaauthserver.feign.SearchFeignService;
import com.zsp.zspoaauthserver.service.LoginService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    SearchFeignService searchFeignService;
    @Override
    public R userLogin(@Valid User user, BindingResult result, HttpServletResponse response, HttpSession session) {
        System.out.println("登陆用户为："+user);
        R r = searchFeignService.searchMemberList();
        String memberListJson = JSON.toJSONString(r.get("memberList"));
        List<Member> memberList = JSON.parseArray(memberListJson, Member.class);
        Member member = (Member)memberList.stream().filter(m -> m.getUserName().equals(user.getUsername())).findAny().orElse(null);
        //判断用户名是否存在,取出这个menber

        if (result.hasErrors())
        {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            System.out.println(errors);

            return R.error().put("loginMsg", JSON.toJSON(errors));
        }
        //判断用户名是否存在
        if (member==null){
            return R.error().put("loginMsg", "用户名不存在");
        }
        boolean checkPassword = member.getUserPassword().equals(user.getPassword());//用户密码是否正确，这个判断出来是相反的结果
        //判断输入格式是否正确，用户名是否符合规范，密码是否符合规范
        if(!checkPassword){
            return  R.error().put("loginMsg","用户名或密码不正确");
        }
        String userToken = JwtUtils.generateJsonWebToken(user);
        response.setHeader("userToken",userToken);
        session.setAttribute("userToken",userToken);
        System.out.println("加密后的token是："+userToken);
        return R.ok().put("loginMsg","登陆成功");
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
        return memberFeignService.memberRegist(userRegist.getUsername(),userRegist.getPassword(),userRegist.getPhone());

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
