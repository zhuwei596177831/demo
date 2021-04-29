package com.example.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;

/**
 * @author 朱伟伟
 * @date 2021-04-29 17:32:48
 * @description redis配置
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisOperations.class)
public class RedisConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FastJsonRedisTemplate fastJsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        FastJsonRedisTemplate fastJsonRedisTemplate = new FastJsonRedisTemplate();
        fastJsonRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return fastJsonRedisTemplate;
    }

//    @Bean
//    @ConditionalOnMissingBean(name = "fastJsonRedisTemplate")
//    public RedisTemplate<String, Object> fastJsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> fastJsonRedisTemplate = new RedisTemplate<>();
//        fastJsonRedisTemplate.setConnectionFactory(redisConnectionFactory);
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteDateUseDateFormat
//        );
//        fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
//        fastJsonRedisTemplate.setKeySerializer(RedisSerializer.string());
//        fastJsonRedisTemplate.setValueSerializer(fastJsonRedisSerializer);
//        fastJsonRedisTemplate.setHashKeySerializer(RedisSerializer.string());
//        fastJsonRedisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
//        return fastJsonRedisTemplate;
//    }

}
