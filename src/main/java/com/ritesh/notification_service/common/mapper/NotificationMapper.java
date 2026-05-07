package com.ritesh.notification_service.common.mapper;

import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.dto.response.NotificationResponse;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationResponse toResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .category(notification.getCategory())
                .channel(notification.getChannel())
                .status(notification.getStatus())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}