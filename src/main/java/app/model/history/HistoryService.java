package app.model.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface HistoryService {
    public History save(History history);
}
