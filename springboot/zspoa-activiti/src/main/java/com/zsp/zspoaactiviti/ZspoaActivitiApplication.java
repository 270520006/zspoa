package com.zsp.zspoaactiviti;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication

public class ZspoaActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZspoaActivitiApplication.class, args);
	}

}
