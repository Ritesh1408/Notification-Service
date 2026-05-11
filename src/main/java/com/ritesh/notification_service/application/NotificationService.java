package com.ritesh.notification_service.application;

import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.dto.NotificationCreateRequest;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    Notification createNotification(String userId, NotificationCreateRequest request);

    List<Notification> getUserNotifications(String userId);

    Notification markAsRead(UUID notificationId, String userId);

}