package com.ritesh.notification_service.application;

import com.ritesh.notification_service.domain.entity.Notification;

public record MarkAsReadResult(
        Notification notification,
        boolean changed
) {
}