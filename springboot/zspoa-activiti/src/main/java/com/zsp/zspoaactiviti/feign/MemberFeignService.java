package com.zsp.zspoaactiviti.feign;

import com.zsp.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "zspoa-member")
public interface MemberFeignService   {
    @GetMapping("member/list")
    public R memberList();
    @GetMapping("member/add/{userName}/{userPassword}")
    public R addMember(@PathVariable(value = "userName")String userName, @PathVariable(value = "userPassword") String userPassword );
    @GetMapping("member/activity/select/{userId}")
    public R selectByUserId(@PathVariable(value = "userId") Long userId);
}