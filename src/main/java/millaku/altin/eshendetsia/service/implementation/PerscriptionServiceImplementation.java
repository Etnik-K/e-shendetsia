package millaku.altin.eshendetsia.service.implementation;

import millaku.altin.eshendetsia.model.entity.Perscription;
import millaku.altin.eshendetsia.repository.PerscriptionRepository;
import millaku.altin.eshendetsia.service.interfaces.PerscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit per menaxhimin e recetave
 */
@Service
public class PerscriptionServiceImplementation implements PerscriptionService {
    
    private PerscriptionRepository persciptionRepository;

    /**
     * Konstruktori i klases
     *
     * @param persciptionRepository repository per ruajtjen e recetave
     */
    @Autowired
    public PerscriptionServiceImplementation(PerscriptionRepository persciptionRepository) {
        this.persciptionRepository = persciptionRepository;
    }

    /**
     * Gjen recetat sipas ID se pacientit
     *
     * @param userId ID e pacientit
     * @return lista e recetave
     */
    public List<Perscription> findByUserId(Long userId) {
        return persciptionRepository.findByUserId(userId);
    }

    /**
     * Gjen recetat sipas ID se mjekut
     *
     * @param doctorId ID e mjekut
     * @return lista e recetave
     */
    public List<Perscription> findByDoctorId(Long doctorId) {
        return persciptionRepository.findByDoctorId(doctorId);
    }

    /**
     * Ruan receten e re
     *
     * @param prescription receta qe do te ruhet
     * @return receta e ruajtur
     */
    public Perscription save(Perscription prescription) {
        return persciptionRepository.save(prescription);
    }
}
