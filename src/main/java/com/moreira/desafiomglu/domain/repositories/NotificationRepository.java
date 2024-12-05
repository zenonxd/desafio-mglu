package com.moreira.desafiomglu.domain.repositories;

import com.moreira.desafiomglu.domain.entities.Channel;
import com.moreira.desafiomglu.domain.entities.Notification;
import com.moreira.desafiomglu.domain.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    List<Notification> findByStatusInAndDateBefore(List<Status> status, LocalDateTime dateTime);
}
