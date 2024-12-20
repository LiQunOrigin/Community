package com.liqun.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.config
 * @className: RedisConfig
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/23 10:25
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置key的序列化方式
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式
        redisTemplate.setValueSerializer(RedisSerializer.json());
        // 设置hash的key的序列化方式
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // 设置hash的value的序列化方式
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
