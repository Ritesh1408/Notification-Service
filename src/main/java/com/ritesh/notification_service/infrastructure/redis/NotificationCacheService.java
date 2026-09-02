package com.ritesh.notification_service.infrastructure.redis;

import java.util.List;

public interface NotificationCacheService {

    void incrementUnreadCount(String userId);

    void decrementUnreadCount(String userId);

    long getUnreadCount(String userId);

    void cacheLatestNotification(String userId, String notificationJson);

    List<String> getLatestNotifications(String userId);
}