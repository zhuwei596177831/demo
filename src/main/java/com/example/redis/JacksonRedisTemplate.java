package com.example.redis;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author 朱伟伟
 * @date 2021-04-29 23:33:41
 * @description 自定义JacksonRedisTemplate
 */
public class JacksonRedisTemplate extends RedisTemplate<String, Object> {

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //设置时区 东八区
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        //date类型 全局配置格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //配置 java8的支持
        objectMapper.registerModule(new JavaTimeModule());
    }

    public JacksonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(jackson2JsonRedisSerializer);
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(jackson2JsonRedisSerializer);
        setConnectionFactory(redisConnectionFactory);
    }
}
