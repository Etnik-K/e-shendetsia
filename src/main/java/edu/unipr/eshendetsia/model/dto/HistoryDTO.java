package edu.unipr.eshendetsia.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqeson transferimin e te dhenave per historikun e pacientit
 * Perdoret per te menaxhuar te dhenat midis sherbimeve dhe kontrollerve
 */
@Getter
@Setter
public class HistoryDTO {
        private Long userId;
        private Long doctorId;
        private String report;
        private LocalDateTime date;
}
