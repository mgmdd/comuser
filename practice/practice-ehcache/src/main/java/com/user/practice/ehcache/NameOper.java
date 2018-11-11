package com.user.practice.ehcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

//@Service
@Component
public class NameOper {
    private long timestamp = System.currentTimeMillis();

    public NameOper() {

    }

    @Cacheable(value = "name_cache")
    public long getTimestamp() {
        System.out.println("gettimestamp called");
        return timestamp;
    }

}
