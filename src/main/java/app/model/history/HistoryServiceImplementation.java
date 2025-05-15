package app.model.history;

import app.exception.UnauthorizedException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImplementation implements HistoryService{

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImplementation(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    public History save(History history) throws UnauthorizedException, JWTVerificationException {
        return historyRepository.save(history);
    }
}
