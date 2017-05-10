package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import in.expedite.core.entity.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, String> {

	
}
