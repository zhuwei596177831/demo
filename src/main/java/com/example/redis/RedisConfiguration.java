package com.example.redis;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.example.generic.BaseEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author 朱伟伟
 * @date 2021-04-29 17:32:48
 * @description
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisOperations.class)
public class RedisConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "fastJsonRedisTemplate")
    public RedisTemplate<String, Object> fastJsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> fastJsonRedisTemplate = new RedisTemplate<>();
        fastJsonRedisTemplate.setConnectionFactory(redisConnectionFactory);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat
        );
        fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
//        fastJsonRedisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        fastJsonRedisTemplate.setKeySerializer(RedisSerializer.string());
        fastJsonRedisTemplate.setValueSerializer(fastJsonRedisSerializer);
        fastJsonRedisTemplate.setHashKeySerializer(RedisSerializer.string());
        fastJsonRedisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        fastJsonRedisTemplate.afterPropertiesSet();
        return fastJsonRedisTemplate;
    }

}
