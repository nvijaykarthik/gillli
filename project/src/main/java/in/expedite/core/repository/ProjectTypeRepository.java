package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.core.entity.ProjectType;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, String> {

}
