package com.test.signservice.redis.repository;


import com.test.signservice.model.Topic;

public interface RedisRepository<T> {
    Topic add(T t);

    Topic add(String key, T t);

    T get(String key);

    T getAndDelete(String key);

    void remove(String key);
}
