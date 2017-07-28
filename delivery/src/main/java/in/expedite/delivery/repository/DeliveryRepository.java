package in.expedite.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.expedite.delivery.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	List<Delivery> findByProjectId(Long projectId);

	@Query("select d from Delivery d where d.createdDate =(select max(createdDate) from Delivery where applicationId=:appId) and d.applicationId=:appId")
	Delivery findVersion(@Param(value = "appId") Long appId);

}
