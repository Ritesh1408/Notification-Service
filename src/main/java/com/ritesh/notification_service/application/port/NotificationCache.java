package com.ritesh.notification_service.application.port;

import java.util.List;

public interface NotificationCache {

    void incrementUnreadCount(String userId);

    void decrementUnreadCount(String userId);

    long getUnreadCount(String userId);

    void cacheLatestNotification(String userId, String notificationJson);

    List<String> getLatestNotifications(String userId);
}