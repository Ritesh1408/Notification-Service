package com.ritesh.notification_service.api;

import com.ritesh.notification_service.application.NotificationService;
import com.ritesh.notification_service.common.response.ApiResponse;
import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.common.mapper.NotificationMapper;
import com.ritesh.notification_service.dto.response.NotificationResponse;
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
    private final NotificationMapper notificationMapper;

    /**
     * Create Notification
     */
    @PostMapping
    public ApiResponse<NotificationResponse> createNotification(
            @RequestHeader("x-user-id") String userId,
            @Valid @RequestBody NotificationCreateRequest request
    ) {

        Notification notification =
                notificationService.createNotification(userId, request);

        NotificationResponse response =
                notificationMapper.toResponse(notification);

        return ApiResponse.<NotificationResponse>builder()
                .success(true)
                .message("Notification created successfully")
                .data(response)
                .build();
    }

    /**
     * Get User Notifications
     */
    @GetMapping
    public ApiResponse<List<NotificationResponse>> getNotifications(
            @RequestHeader("x-user-id") String userId
    ) {

        List<NotificationResponse> responses =
                notificationService.getUserNotifications(userId)
                        .stream()
                        .map(notificationMapper::toResponse)
                        .toList();

        return ApiResponse.<List<NotificationResponse>>builder()
                .success(true)
                .message("Notifications fetched successfully")
                .data(responses)
                .build();
    }
}