package in.expedite.environment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.environment.entity.EnvironmentDetails;

public interface EnvironmentDetailsRepo extends JpaRepository<EnvironmentDetails, Long> {

	EnvironmentDetails findByApplicationIdAndEnvironmentId(Long applicationId, Long environmentId);

}
