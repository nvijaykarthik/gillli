package in.expedite.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.delivery.entity.Artifacts;

public interface ArtifactsRepository extends JpaRepository<Artifacts, Long> {

	List<Artifacts> findByDeliveryId(Long deliveryId);
}
