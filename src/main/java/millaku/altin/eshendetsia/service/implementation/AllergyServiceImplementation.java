package millaku.altin.eshendetsia.service.implementation;

import millaku.altin.eshendetsia.model.entity.Allergy;
import millaku.altin.eshendetsia.model.entity.UserAllergy;
import millaku.altin.eshendetsia.repository.AllergyRepository;
import millaku.altin.eshendetsia.service.interfaces.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Implementimi i sherbimit per menaxhimin e alergjive
 * perfshin logjiken kryesore te biznesit per te gjitha operacionet
 * qe lidhen me alergji
 */
@Service
public class AllergyServiceImplementation implements AllergyService {

    private final AllergyRepository allergyRepository;

    /**
     * Konstruktori i klases
     *
     * @param allergyRepository repository per akses ne te dhenat e alergjive
     */
    @Autowired
    public AllergyServiceImplementation(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    /**
     * Ruan nje alergji te re ne sistem
     *
     * @param allergy objekti i alergjise per tu ruajtur
     * @return alergji e ruajtur
     */
    public Allergy save(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    /**
     * Merr te gjitha alergjet e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e alergjive te perdoruesit
     */
    public List<Allergy> getByUserId(Long userId) {
        return allergyRepository.findByUsers(Set.of(new UserAllergy()));
    }

    /**
     * Fshin nje alergji nga sistemi
     *
     * @param id ID e alergjise per tu fshire
     */
    public void delete(Long id) {
        allergyRepository.deleteById(id);
    }
}
