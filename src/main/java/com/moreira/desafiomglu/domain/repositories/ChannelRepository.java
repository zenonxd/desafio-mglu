package com.moreira.desafiomglu.domain.repositories;

import com.moreira.desafiomglu.domain.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
