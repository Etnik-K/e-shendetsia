package UnitTest;

import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.repository.FeedbackRepository;
import edu.unipr.eshendetsia.service.implementation.FeedbackServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplementationTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    private FeedbackServiceImplementation feedbackService;

    @BeforeEach
    void setUp() {
        feedbackService = new FeedbackServiceImplementation(feedbackRepository);
    }

    @Test
    void save_ShouldReturnSavedFeedback() {
        Feedback feedback = new Feedback();
        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        Feedback result = feedbackService.save(feedback);

        assertEquals(feedback, result);
        verify(feedbackRepository).save(feedback);
    }

    @Test
    void getByDoctorId_ShouldReturnFeedbackList() {
        Long doctorId = 1L;
        List<Feedback> expectedFeedbacks = Arrays.asList(new Feedback(), new Feedback());
        when(feedbackRepository.findByDoctorId(doctorId)).thenReturn(expectedFeedbacks);

        List<Feedback> result = feedbackService.getByDoctorId(doctorId);

        assertEquals(expectedFeedbacks, result);
        verify(feedbackRepository).findByDoctorId(doctorId);
    }

    @Test
    void getByUserId_ShouldReturnFeedbackList() {
        Long userId = 1L;
        List<Feedback> expectedFeedbacks = Arrays.asList(new Feedback(), new Feedback());
        when(feedbackRepository.findByUserId(userId)).thenReturn(expectedFeedbacks);

        List<Feedback> result = feedbackService.getByUserId(userId);

        assertEquals(expectedFeedbacks, result);
        verify(feedbackRepository).findByUserId(userId);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        Long id = 1L;

        feedbackService.delete(id);

        verify(feedbackRepository).deleteById(id);
    }
}