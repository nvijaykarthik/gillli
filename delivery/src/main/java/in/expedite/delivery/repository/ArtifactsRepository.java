package in.expedite.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.delivery.entity.Artifacts;

public interface ArtifactsRepository extends JpaRepository<Artifacts, Long> {

}
