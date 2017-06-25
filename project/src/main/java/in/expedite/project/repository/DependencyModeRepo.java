package in.expedite.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.project.entity.DependencyMode;

public interface DependencyModeRepo extends JpaRepository<DependencyMode, String> {

}
