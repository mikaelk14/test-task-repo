package com.service.gateway.redis.repository;

import com.service.gateway.model.Topic;

public interface RedisRepository<T> {
    Topic add(T t);

    Topic add(String key, T t);

    T get(String key);

    T getAndDelete(String key);

    void remove(String key);
}
