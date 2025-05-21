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

class InsuranceServiceImplementationTest {

    @Mock
    private InsuranceRepository insuranceRepository;

    private InsuranceServiceImplementation insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        insuranceService = new InsuranceServiceImplementation(insuranceRepository);
    }

    @Test
    void testSave() {
        Insurance insurance = new Insurance();
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);

        Insurance savedInsurance = insuranceService.save(insurance);

        assertNotNull(savedInsurance);
        verify(insuranceRepository, times(1)).save(insurance);
    }

    @Test
    void testGetByUserId() {
        Long userId = 1L;
        List<Insurance> insurances = Arrays.asList(new Insurance(), new Insurance());
        when(insuranceRepository.findByUserId(userId)).thenReturn(insurances);

        List<Insurance> result = insuranceService.getByUserId(userId);

        assertEquals(2, result.size());
        verify(insuranceRepository, times(1)).findByUserId(userId);
    }

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

    @Test
    void testUpdateStatus_NotFound() {
        Long id = 1L;
        when(insuranceRepository.findById(id)).thenReturn(Optional.empty());

        Insurance result = insuranceService.updateStatus(id, true);

        assertNull(result);
        verify(insuranceRepository, times(1)).findById(id);
        verify(insuranceRepository, never()).save(any());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        insuranceService.delete(id);

        verify(insuranceRepository, times(1)).deleteById(id);
    }
}