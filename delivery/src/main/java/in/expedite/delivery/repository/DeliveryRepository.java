package in.expedite.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.delivery.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	List<Delivery> findByProjectId(Long projectId);

}
