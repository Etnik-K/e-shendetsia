package app.model.history;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HistoryDTO {
        private Long userId;
        private Long doctorId;
        private String report;
        private LocalDateTime date;
}
