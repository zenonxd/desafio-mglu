package com.moreira.desafiomglu.domain.services;

import com.moreira.desafiomglu.domain.entities.Channel;
import com.moreira.desafiomglu.domain.entities.Notification;
import com.moreira.desafiomglu.domain.entities.Status;
import com.moreira.desafiomglu.domain.repositories.NotificationRepository;
import com.moreira.desafiomglu.interfaces.dtos.NotificationResponse;
import com.moreira.desafiomglu.interfaces.dtos.NotificationScheduleRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public void scheduleNotification(NotificationScheduleRequest dto) {
        notificationRepository.save(dto.toNotification());
    }

    public NotificationResponse findNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        return NotificationResponse.from(notification);
    }

    public void updateNotificationById(Long id) {
        var notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setStatus(Status.Values.CANCELED.toStatus());

        notificationRepository.save(notification);
    }

    public void checkNotifications(LocalDateTime dateTime) {
        var notifications = notificationRepository.findByStatusInAndDateBefore(
                List.of(Status.Values.PENDING.toStatus(),
                        Status.Values.ERROR.toStatus()),
                dateTime
        );

        notifications.forEach(sendNotification());

        notificationRepository.saveAll(notifications);
    }

    private static Consumer<Notification> sendNotification() {
        return notification -> notification.setStatus(Status.Values.SUCCESS.toStatus());
    }
}
