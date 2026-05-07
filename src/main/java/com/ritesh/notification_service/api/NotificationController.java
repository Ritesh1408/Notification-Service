package com.ritesh.notification_service.api;

import com.ritesh.notification_service.application.NotificationService;
import com.ritesh.notification_service.common.response.ApiResponse;
import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.dto.NotificationCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Create Notification
     */
    @PostMapping
    public ApiResponse<Notification> createNotification(
            @RequestHeader("x-user-id") String userId,
            @Valid @RequestBody NotificationCreateRequest request
    ) {

        Notification notification = notificationService.createNotification(userId, request);

        return ApiResponse.<Notification>builder()
                .success(true)
                .message("Notification created successfully")
                .data(notification)
                .build();
    }

    /**
     * Get User Notifications
     */
    @GetMapping
    public ApiResponse<List<Notification>> getNotifications(
            @RequestHeader("x-user-id") String userId
    ) {

        List<Notification> notifications = notificationService.getUserNotifications(userId);

        return ApiResponse.<List<Notification>>builder()
                .success(true)
                .message("Notifications fetched successfully")
                .data(notifications)
                .build();
    }
}