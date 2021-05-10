package com.zsp.zspoamember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@EnableDiscoveryClient
@SpringBootApplication
@EnableRedisHttpSession


public class ZspoaMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZspoaMemberApplication.class, args);
    }

}
