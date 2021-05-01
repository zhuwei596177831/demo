package com.example.redis;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author 朱伟伟
 * @date 2021-04-29 23:33:41
 * @description 自定义FastJsonRedisTemplate
 */
public class FastJsonRedisTemplate extends RedisTemplate<String, Object> {

    public FastJsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat
        );
        fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(fastJsonRedisSerializer);
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(fastJsonRedisSerializer);
        setConnectionFactory(redisConnectionFactory);
    }
}
