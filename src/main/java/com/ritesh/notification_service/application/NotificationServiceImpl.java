package com.ritesh.notification_service.application;

import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.domain.enums.NotificationStatus;
import com.ritesh.notification_service.dto.NotificationCreateRequest;
import com.ritesh.notification_service.infrastructure.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(String userId, Object req) {

        NotificationCreateRequest request = (NotificationCreateRequest) req;

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
}