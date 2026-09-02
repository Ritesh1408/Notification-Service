package com.ritesh.notification_service.api;

import com.ritesh.notification_service.api.dto.NotificationCreateRequest;
import com.ritesh.notification_service.api.dto.NotificationResponse;
import com.ritesh.notification_service.application.NotificationFacade;
import com.ritesh.notification_service.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationFacade notificationFacade;

    @PostMapping
    public ApiResponse<NotificationResponse> createNotification(
            @RequestHeader("x-user-id") String userId,
            @Valid @RequestBody NotificationCreateRequest request
    ) {

        NotificationResponse response =
                notificationFacade.createNotification(
                        userId,
                        request
                );

        return ApiResponse.<NotificationResponse>builder()
                .success(true)
                .message("Notification created successfully")
                .data(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<NotificationResponse>> getNotifications(
            @RequestHeader("x-user-id") String userId
    ) {

        List<NotificationResponse> responses =
                notificationFacade.getUserNotifications(userId);

        return ApiResponse.<List<NotificationResponse>>builder()
                .success(true)
                .message("Notifications fetched successfully")
                .data(responses)
                .build();
    }

    @GetMapping("/unread-count")
    public ApiResponse<Long> getUnreadCount(
            @RequestHeader("x-user-id") String userId
    ) {

        long unreadCount =
                notificationFacade.getUnreadCount(userId);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("Unread count fetched successfully")
                .data(unreadCount)
                .build();
    }

    @GetMapping("/latest")
    public ApiResponse<List<NotificationResponse>> getLatestNotifications(
            @RequestHeader("x-user-id") String userId
    ) {

        List<NotificationResponse> responses =
                notificationFacade.getLatestNotifications(userId);

        return ApiResponse.<List<NotificationResponse>>builder()
                .success(true)
                .message("Latest notifications fetched successfully")
                .data(responses)
                .build();
    }

    @PatchMapping("/{id}/read")
    public ApiResponse<NotificationResponse> markAsRead(
            @PathVariable UUID id,
            @RequestHeader("x-user-id") String userId
    ) {

        NotificationResponse response =
                notificationFacade.markAsRead(id, userId);

        return ApiResponse.<NotificationResponse>builder()
                .success(true)
                .message("Notification marked as read")
                .data(response)
                .build();
    }
}