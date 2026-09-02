package com.ritesh.notification_service.application;

import com.ritesh.notification_service.common.mapper.NotificationMapper;
import com.ritesh.notification_service.common.util.JsonUtil;
import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.api.dto.NotificationCreateRequest;
import com.ritesh.notification_service.api.dto.NotificationResponse;
import com.ritesh.notification_service.application.port.NotificationCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationFacade {

    private final NotificationService notificationService;
    private final NotificationCache notificationCache;
    private final NotificationMapper notificationMapper;
    private final JsonUtil jsonUtil;

    public NotificationResponse createNotification(
            String userId,
            NotificationCreateRequest request
    ) {

        Notification notification =
                notificationService.createNotification(userId, request);

        NotificationResponse response =
                notificationMapper.toResponse(notification);

        notificationCache.incrementUnreadCount(userId);

        notificationCache.cacheLatestNotification(
                userId,
                jsonUtil.toJson(response)
        );

        return response;
    }

    public List<NotificationResponse> getUserNotifications(
            String userId
    ) {

        return notificationService
                .getUserNotifications(userId)
                .stream()
                .map(notificationMapper::toResponse)
                .toList();
    }

    public NotificationResponse markAsRead(
            UUID notificationId,
            String userId
    ) {

        MarkAsReadResult result =
                notificationService.markAsRead(
                        notificationId,
                        userId
                );

        if (result.changed()) {
            notificationCache.decrementUnreadCount(userId);
        }

        return notificationMapper.toResponse(
                result.notification()
        );
    }

    public long getUnreadCount(String userId) {
        return notificationCache.getUnreadCount(userId);
    }

    public List<NotificationResponse> getLatestNotifications(String userId) {

        return notificationCache
                .getLatestNotifications(userId)
                .stream()
                .map(json -> jsonUtil.fromJson(
                        json,
                        NotificationResponse.class
                ))
                .toList();
    }
}