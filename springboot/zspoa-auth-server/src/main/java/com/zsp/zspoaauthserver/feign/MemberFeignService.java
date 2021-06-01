package com.zsp.zspoaauthserver.feign;


import com.zsp.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "zspoa-member")
public interface MemberFeignService {
    @PostMapping("member/regist")
    public R memberRegist(String userName,String userPhone);
}
