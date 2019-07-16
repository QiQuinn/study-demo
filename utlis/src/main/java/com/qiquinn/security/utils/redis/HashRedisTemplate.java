package com.qiquinn.security.utils.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/16
 * @Modified By:
 */
@Component
public class HashRedisTemplate extends RedisTemplate {
    public HashRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        this.setConnectionFactory(redisConnectionFactory);
        this.setKeySerializer(new StringRedisSerializer());
        this.setValueSerializer(new StringRedisSerializer());
        this.setHashKeySerializer(new StringRedisSerializer());
        this.setHashValueSerializer(new StringRedisSerializer());
        this.afterPropertiesSet();
    }
}
