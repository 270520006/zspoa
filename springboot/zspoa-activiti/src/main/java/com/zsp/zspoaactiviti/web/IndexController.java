package com.zsp.zspoaactiviti.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"/","index.html"})
    public String indexPage(){
        return "home/login";
    }
}
