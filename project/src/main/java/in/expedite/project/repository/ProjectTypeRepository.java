package in.expedite.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.project.entity.ProjectType;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, String> {

}
