package com.moreira.desafiomglu.interfaces;

import com.moreira.desafiomglu.domain.services.NotificationService;
import com.moreira.desafiomglu.interfaces.dtos.NotificationResponse;
import com.moreira.desafiomglu.interfaces.dtos.NotificationScheduleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping(value = "/schedule")
    public ResponseEntity<Void> scheduleNotification(@RequestBody NotificationScheduleRequest dto) {

        notificationService.scheduleNotification(dto);

        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NotificationResponse> findNotificationById(@PathVariable Long id) {

        var response = notificationService.findNotificationById(id);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> updateNotificationById(@PathVariable Long id) {

        notificationService.updateNotificationById(id);

        return ResponseEntity.noContent().build();
    }
}
