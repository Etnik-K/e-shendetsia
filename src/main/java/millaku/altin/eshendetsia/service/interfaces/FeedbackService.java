package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback save(Feedback feedback);

    List<Feedback> getByDoctorId(Long doctorId);

    List<Feedback> getByUserId(Long userId);

    void delete(Long id);

}
