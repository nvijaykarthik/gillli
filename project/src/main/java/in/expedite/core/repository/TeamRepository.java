package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.core.entity.Project;

public interface TeamRepository extends JpaRepository<Project,Long> {

}
