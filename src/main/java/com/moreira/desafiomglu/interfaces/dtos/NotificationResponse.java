package com.moreira.desafiomglu.interfaces.dtos;

import com.moreira.desafiomglu.domain.entities.Channel;
import com.moreira.desafiomglu.domain.entities.Notification;
import com.moreira.desafiomglu.domain.entities.Status;

import java.time.LocalDateTime;

public record NotificationResponse(Long id,
                                   LocalDateTime date,
                                   String receiver,
                                   String message,
                                   Channel channel,
                                   Status status) {

    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getDate(),
                notification.getReceiver(),
                notification.getMessage(),
                notification.getChannel(),
                notification.getStatus()
        );
    }
}
