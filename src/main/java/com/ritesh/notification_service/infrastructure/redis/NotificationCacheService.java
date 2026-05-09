package com.ritesh.notification_service.infrastructure.redis;

public interface NotificationCacheService {

    void incrementUnreadCount(String userId);

    void decrementUnreadCount(String userId);

    long getUnreadCount(String userId);
}