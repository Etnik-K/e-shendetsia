package millaku.altin.eshendetsia.service.implementation;

import millaku.altin.eshendetsia.exception.concrete.UnauthorizedException;
import millaku.altin.eshendetsia.model.entity.History;
import millaku.altin.eshendetsia.repository.HistoryRepository;
import millaku.altin.eshendetsia.service.interfaces.HistoryService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sherbimi qe implementon logjiken e historise se sistemit
 * dhe menaxhon te dhenat e historise
 */
@Service
public class HistoryServiceImplementation implements HistoryService {

    private final HistoryRepository historyRepository;

    /**
     * Konstruktori i klases qe merr repository-n e historise
     *
     * @param historyRepository repository per menaxhimin e te dhenave te historise
     */
    @Autowired
    public HistoryServiceImplementation(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    /**
     * Ruan nje rekord te ri ne historine e sistemit
     *
     * @param history rekordi i historise per tu ruajtur
     * @return rekordi i ruajtur i historise
     * @throws UnauthorizedException    nese perdoruesi nuk ka te drejta
     * @throws JWTVerificationException nese ka problem me token
     */
    public History save(History history) throws UnauthorizedException, JWTVerificationException {
        return historyRepository.save(history);
    }
}
