package millaku.altin.eshendetsia.repository;

import millaku.altin.eshendetsia.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserId(Long userId);
    List<Bill> findByIsPaid(boolean isPaid);
}
