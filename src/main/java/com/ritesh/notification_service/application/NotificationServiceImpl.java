package com.ritesh.notification_service.application;

import com.ritesh.notification_service.api.dto.NotificationCreateRequest;
import com.ritesh.notification_service.common.exception.ResourceNotFoundException;
import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.domain.enums.NotificationStatus;
import com.ritesh.notification_service.infrastructure.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(
            String userId,
            NotificationCreateRequest request
    ) {

        Notification notification = Notification.builder()
                .userId(userId)
                .title(request.getTitle())
                .message(request.getMessage())
                .category(request.getCategory())
                .channel(request.getChannel())
                .status(NotificationStatus.PENDING)
                .isRead(false)
                .payload(request.getPayload())
                .retryCount(0)
                .build();

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public MarkAsReadResult markAsRead(
            UUID notificationId,
            String userId
    ) {

        Notification notification =
                notificationRepository
                        .findByIdAndUserId(notificationId, userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found"
                                )
                        );

        if (notification.isRead()) {
            return new MarkAsReadResult(
                    notification,
                    false
            );
        }

        notification.setRead(true);

        Notification savedNotification =
                notificationRepository.save(notification);

        return new MarkAsReadResult(
                savedNotification,
                true
        );
    }
}