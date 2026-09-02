package com.ritesh.notification_service.application;

import com.ritesh.notification_service.api.dto.NotificationCreateRequest;
import com.ritesh.notification_service.domain.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    Notification createNotification(
            String userId,
            NotificationCreateRequest request
    );

    List<Notification> getUserNotifications(
            String userId
    );

    MarkAsReadResult markAsRead(
            UUID notificationId,
            String userId
    );
}