package UnitTest;

import edu.unipr.eshendetsia.model.entity.Insurance;
import edu.unipr.eshendetsia.repository.InsuranceRepository;
import edu.unipr.eshendetsia.service.implementation.InsuranceServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


/**
 * Klasa per testimin e implementimit te sherbimit te sigurimit.
 * Perdor Mockito per te simuluar repository-n.
 */
class InsuranceServiceImplementationTest {

    @Mock
    private InsuranceRepository insuranceRepository;

    private InsuranceServiceImplementation insuranceService;

    /**
     * Inicializon mjedisin e testimit para cdo testi.
     * Krijon mock objekte dhe instance te sherbimit.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        insuranceService = new InsuranceServiceImplementation(insuranceRepository);
    }

    /**
     * Teston ruajtjen e nje instance te sigurimit.
     * Verifikon nese metoda save thirret nje here dhe kthen rezultatin e pritur.
     */
    @Test
    void testSave() {
        Insurance insurance = new Insurance();
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);

        Insurance savedInsurance = insuranceService.save(insurance);

        assertNotNull(savedInsurance);
        verify(insuranceRepository, times(1)).save(insurance);
    }

    /**
     * Teston marrjen e sigurimeve sipas ID se perdoruesit.
     * Kontrollon nese lista e kthyer permban numrin e sakte te elementeve.
     */
    @Test
    void testGetByUserId() {
        Long userId = 1L;
        List<Insurance> insurances = Arrays.asList(new Insurance(), new Insurance());
        when(insuranceRepository.findByUserId(userId)).thenReturn(insurances);

        List<Insurance> result = insuranceService.getByUserId(userId);

        assertEquals(2, result.size());
        verify(insuranceRepository, times(1)).findByUserId(userId);
    }

    /**
     * Teston perditesimin e statusit te sigurimit.
     * Verifikon nese statusi ndryshohet me sukses dhe ruhet.
     */
    @Test
    void testUpdateStatus() {
        Long id = 1L;
        Insurance insurance = new Insurance();
        when(insuranceRepository.findById(id)).thenReturn(Optional.of(insurance));
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);

        Insurance updatedInsurance = insuranceService.updateStatus(id, true);

        assertNotNull(updatedInsurance);
        assertTrue(updatedInsurance.isActive());
        verify(insuranceRepository, times(1)).findById(id);
        verify(insuranceRepository, times(1)).save(insurance);
    }

    /**
     * Teston rastin kur sigurimi nuk gjendet gjate perditesimit te statusit.
     * Kontrollon sjelljen kur ID e kerkuar nuk ekziston.
     */
    @Test
    void testUpdateStatus_NotFound() {
        Long id = 1L;
        when(insuranceRepository.findById(id)).thenReturn(Optional.empty());

        Insurance result = insuranceService.updateStatus(id, true);

        assertNull(result);
        verify(insuranceRepository, times(1)).findById(id);
        verify(insuranceRepository, never()).save(any());
    }

    /**
     * Teston fshirjen e sigurimit.
     * Verifikon nese metoda delete thirret nje here me ID-ne e dhene.
     */
    @Test
    void testDelete() {
        Long id = 1L;

        insuranceService.delete(id);

        verify(insuranceRepository, times(1)).deleteById(id);
    }
}