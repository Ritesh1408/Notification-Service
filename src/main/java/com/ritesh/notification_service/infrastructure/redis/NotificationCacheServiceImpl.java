package com.ritesh.notification_service.infrastructure.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationCacheServiceImpl
        implements NotificationCacheService {

    private static final String KEY_PREFIX = "notif:unread:";

    private final StringRedisTemplate redisTemplate;

    @Override
    public void incrementUnreadCount(String userId) {

        redisTemplate.opsForValue()
                .increment(KEY_PREFIX + userId);
    }

    @Override
    public void decrementUnreadCount(String userId) {

        String key = KEY_PREFIX + userId;

        String value = redisTemplate.opsForValue().get(key);

        if (value != null && Long.parseLong(value) > 0) {

            redisTemplate.opsForValue()
                    .decrement(key);
        }
    }

    @Override
    public long getUnreadCount(String userId) {

        String value = redisTemplate.opsForValue()
                .get(KEY_PREFIX + userId);

        return value == null ? 0 : Long.parseLong(value);
    }
}