package com.zsp.zspoagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZspoaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZspoaGatewayApplication.class, args);
    }

}
