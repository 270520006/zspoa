package com.zsp.zspoaauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class SpringSessionConfig {
	
	/**
     * 更换序列化器
     * @return
     */
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer setSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }
    
}
