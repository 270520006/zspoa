package com.zsp.zspoamember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@EnableDiscoveryClient
@SpringBootApplication

public class ZspoaMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZspoaMemberApplication.class, args);
    }

}
