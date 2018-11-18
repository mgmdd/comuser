package com.magicg.practice.spring.cache;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CacheContext<T> {

    private Map<String, T> cache = new ConcurrentHashMap<>();

    public T get(String key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    // 依据 key 来删除缓存中的一条记录
    public void evictCache(String key) {
        cache.remove(key);
    }

    // 清空缓存中的全部记录
    public void evictCache() {
        cache.clear();
    }

}
