package com.moreira.desafiomglu.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notifications")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private String receiver;

    private String message;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Notification(LocalDateTime date, String receiver, String message, Channel channel, Status status) {
        this.date = date;
        this.receiver = receiver;
        this.message = message;
        this.channel = channel;
        this.status = status;
    }


}
