package com.zsp.zspoasearch.feign;

import com.zsp.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "zspoa-member")
public interface MemberFeignService {
    @GetMapping("member/list")
    public R memberList();
}
