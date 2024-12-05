package com.moreira.desafiomglu.interfaces.dtos;

import com.moreira.desafiomglu.domain.entities.Channel;
import com.moreira.desafiomglu.domain.entities.Notification;
import com.moreira.desafiomglu.domain.entities.Status;

import java.time.LocalDateTime;

public record NotificationScheduleRequest(LocalDateTime date,
                                          String receiver,
                                          String message,
                                          Channel.Values channel) {

    public Notification toNotification() {
        return new Notification(
                date,
                receiver,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }
}
