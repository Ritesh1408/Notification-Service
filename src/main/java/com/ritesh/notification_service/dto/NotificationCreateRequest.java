package com.ritesh.notification_service.dto;

import com.ritesh.notification_service.domain.enums.NotificationCategory;
import com.ritesh.notification_service.domain.enums.NotificationChannel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationCreateRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Message is required")
    private String message;

    private NotificationCategory category;

    private NotificationChannel channel;

    private String payload;

}