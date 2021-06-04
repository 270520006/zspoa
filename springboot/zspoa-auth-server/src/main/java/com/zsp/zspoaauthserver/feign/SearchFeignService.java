package com.zsp.zspoaauthserver.feign;

import com.zsp.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "zspoa-search")
public interface SearchFeignService {
    /**
     *
     * @return  返回的是转换成json的集合
     */
    @GetMapping("search/member/list")
    public R searchMemberList();
}
