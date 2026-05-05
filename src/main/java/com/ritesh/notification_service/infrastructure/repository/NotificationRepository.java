package com.ritesh.notification_service.infrastructure.repository;
import com.ritesh.notification_service.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUserId(String userId);

    List<Notification> findByUserIdAndIsReadFalse(String userId);

}