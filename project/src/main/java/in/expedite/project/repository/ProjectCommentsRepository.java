package in.expedite.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.project.entity.ProjectComments;

public interface ProjectCommentsRepository extends JpaRepository<ProjectComments, Long> {

	public List<ProjectComments> findByProjectId(Long projectId);
}
