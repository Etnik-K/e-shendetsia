package edu.unipr.eshendetsia.model.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa qe perfaqeson objektin e transferimit te njoftimeve
 * Perdoret per te menaxhuar te dhenat e njoftimeve midis sherbimeve
 */
@Getter
@Setter
public class NotificationDTO {
    public Long id;
    public String message;
    public boolean isRead;
    public LocalDateTime timestamp;
}
