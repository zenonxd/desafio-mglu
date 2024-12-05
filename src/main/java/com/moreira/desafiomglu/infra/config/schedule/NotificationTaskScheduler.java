package com.moreira.desafiomglu.infra.config.schedule;

import com.moreira.desafiomglu.domain.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationTaskScheduler {

    private final static Logger log = LoggerFactory.getLogger(NotificationTaskScheduler.class);

    private final NotificationService notificationService;

    public NotificationTaskScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void checkTasks() {
        var dateTime = LocalDateTime.now();
        log.info("The time now is {}", dateTime);

        notificationService.checkNotifications(dateTime);
    }
}
