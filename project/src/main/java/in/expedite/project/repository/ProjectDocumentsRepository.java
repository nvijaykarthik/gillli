package in.expedite.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.project.entity.ProjectDocuments;

public interface ProjectDocumentsRepository extends JpaRepository<ProjectDocuments, Long> {

	public List<ProjectDocuments> findByProjectId(Long projectId);
}
