package com.moreira.desafiomglu.domain.repositories;

import com.moreira.desafiomglu.domain.entities.Notification;
import com.moreira.desafiomglu.domain.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
