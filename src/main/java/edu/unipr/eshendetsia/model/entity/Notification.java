package edu.unipr.eshendetsia.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "noticiations")
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String message;

    @Column
    private boolean isRead = false;

    @Column
    private LocalDateTime timestamp = LocalDateTime.now();

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
