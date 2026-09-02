package com.ritesh.notification_service.api.dto;

import com.ritesh.notification_service.domain.enums.NotificationCategory;
import com.ritesh.notification_service.domain.enums.NotificationChannel;
import com.ritesh.notification_service.domain.enums.NotificationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class NotificationResponse {

    private UUID id;

    private String title;

    private String message;

    private NotificationCategory category;

    private NotificationChannel channel;

    private NotificationStatus status;

    private boolean isRead;

    private Instant createdAt;
}