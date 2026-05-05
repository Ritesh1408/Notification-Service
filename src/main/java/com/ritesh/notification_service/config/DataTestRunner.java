package com.ritesh.notification_service.config;

import com.ritesh.notification_service.domain.entity.Notification;
import com.ritesh.notification_service.domain.enums.NotificationCategory;
import com.ritesh.notification_service.domain.enums.NotificationChannel;
import com.ritesh.notification_service.domain.enums.NotificationStatus;
import com.ritesh.notification_service.infrastructure.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataTestRunner implements CommandLineRunner {

    private final NotificationRepository notificationRepository;

    @Override
    public void run(String... args) {

        Notification notification = Notification.builder()
                .userId("user_123")
                .title("Payment Received")
                .message("You received a payment of $200")
                .category(NotificationCategory.PAYMENT)
                .channel(NotificationChannel.EMAIL)
                .status(NotificationStatus.PENDING)
                .isRead(false)
                .payload("{}")
                .retryCount(0)
                .build();

        notificationRepository.save(notification);

        System.out.println("Test notification saved!");
    }
}