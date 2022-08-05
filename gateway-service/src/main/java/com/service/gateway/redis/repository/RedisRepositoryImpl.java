package com.service.gateway.redis.repository;

import com.service.gateway.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository<String> {
    private static final String KEY = "1111";

    private final StringRedisTemplate redisTemplate;
    private ValueOperations valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public Topic add(String s) {
        String key = UUID.randomUUID().toString();
        valueOperations.set(key, s);

        return Topic.builder()
                .key(key)
                .build();
    }

    @Override
    public Topic add(String key, String s) {
        valueOperations.set(key, s);

        return Topic.builder().key(key).build();
    }

    @Override
    public String get(String key) {
        return (String) valueOperations.get(key);
    }

    @Override
    public String getAndDelete(String key) {
        return (String) valueOperations.getAndDelete(key);
    }

    @Override
    public void remove(String key) {
        valueOperations.getOperations().delete(List.of(key));
    }
}
