package com.ritesh.notification_service.dto;

import com.ritesh.notification_service.domain.enums.NotificationCategory;
import com.ritesh.notification_service.domain.enums.NotificationChannel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    private NotificationCategory category;

    private NotificationChannel channel;

    private String payload;

}