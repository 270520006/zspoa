package com.zsp.zspoaactiviti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZspoaActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZspoaActivitiApplication.class, args);
	}

}
