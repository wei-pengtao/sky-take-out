package com.sky.test;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class SpringDataRedisTest {

//    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("name", "小明");
        redisTemplate.opsForValue().set("age", 18, 20, TimeUnit.SECONDS);
    }
}
