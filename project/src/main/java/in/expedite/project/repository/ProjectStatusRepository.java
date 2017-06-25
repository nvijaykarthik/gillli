package in.expedite.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.project.entity.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, String> {

	
}
