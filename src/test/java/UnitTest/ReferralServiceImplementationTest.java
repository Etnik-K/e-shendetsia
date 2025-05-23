package UnitTest;

import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.repository.ReferralRepository;
import edu.unipr.eshendetsia.service.implementation.ReferralServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test klasa per ReferralService Implementation
 * Teston te gjitha metodat e implementuara ne ReferralServiceImplementation
 */
class ReferralServiceImplementationTest {

    @Mock
    private ReferralRepository referralRepository;

    private ReferralServiceImplementation referralService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        referralService = new ReferralServiceImplementation(referralRepository);
    }

    /**
     * Teston metoden save duke verifikuar ruajtjen e referimit
     */
    @Test
    void testSave() {
        Referral referral = new Referral();
        when(referralRepository.save(referral)).thenReturn(referral);

        Referral savedReferral = referralService.save(referral);

        assertNotNull(savedReferral);
        verify(referralRepository).save(referral);
    }

    /**
     * Teston metoden getByPatient duke verifikuar marrjen e referimeve sipas pacientit
     */
    @Test
    void testGetByPatient() {
        Long patientId = 1L;
        List<Referral> expectedReferrals = Arrays.asList(new Referral(), new Referral());
        when(referralRepository.findByPatientId(patientId)).thenReturn(expectedReferrals);

        List<Referral> actualReferrals = referralService.getByPatient(patientId);

        assertEquals(expectedReferrals, actualReferrals);
        verify(referralRepository).findByPatientId(patientId);
    }

    /**
     * Teston metoden getByReceivingDoctor duke verifikuar marrjen e referimeve sipas mjekut pranues
     */
    @Test
    void testGetByReceivingDoctor() {
        Long doctorId = 1L;
        List<Referral> expectedReferrals = Arrays.asList(new Referral(), new Referral());
        when(referralRepository.findByToDoctorId(doctorId)).thenReturn(expectedReferrals);

        List<Referral> actualReferrals = referralService.getByReceivingDoctor(doctorId);

        assertEquals(expectedReferrals, actualReferrals);
        verify(referralRepository).findByToDoctorId(doctorId);
    }

    /**
     * Teston metoden delete duke verifikuar fshirjen e referimit
     */
    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(referralRepository).deleteById(id);

        referralService.delete(id);

        verify(referralRepository).deleteById(id);
    }
}