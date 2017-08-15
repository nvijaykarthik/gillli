package in.expedite.environment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import in.expedite.environment.entity.Environment;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
}
