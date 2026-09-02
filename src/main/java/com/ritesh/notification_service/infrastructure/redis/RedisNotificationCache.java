package com.ritesh.notification_service.infrastructure.redis;

import com.ritesh.notification_service.application.port.NotificationCache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisNotificationCache
        implements NotificationCache {

    private static final String KEY_PREFIX = "notif:unread:";
    private static final String LATEST_PREFIX = "notif:latest:";

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

    @Override
    public void cacheLatestNotification(
            String userId,
            String notificationJson
    ) {

        String key = LATEST_PREFIX + userId;

        redisTemplate.opsForList()
                .leftPush(key, notificationJson);

        redisTemplate.opsForList()
                .trim(key, 0, 49);
    }

    @Override
    public List<String> getLatestNotifications(String userId) {

        String key = LATEST_PREFIX + userId;

        List<String> notifications =
                redisTemplate.opsForList()
                        .range(key, 0, 49);

        return notifications == null ? List.of() : notifications;
    }
}