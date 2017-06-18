package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.core.entity.DependencyMode;

public interface DependencyModeRepo extends JpaRepository<DependencyMode, String> {

}
