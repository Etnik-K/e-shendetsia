package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.Insurance;

import java.util.List;

public interface InsuranceService {

    Insurance save(Insurance insurance);

    List<Insurance> getByUserId(Long userId);

    Insurance updateStatus(Long id, boolean active);

    void delete(Long id);

}
