package in.expedite.project.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import in.expedite.project.entity.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project,Long>,JpaSpecificationExecutor<Project>{

	@Query("select p from Project p where lower(p.name) like %:query% or p.id like %:query%")
	public List<Project> searchProject(@Param(value = "query") String query);

	@Query("select p from Project p where p.type='Program' and (lower(p.name) like %:query% or p.id like %:query% )")
	public List<Project> searchPrograms(@Param(value = "query") String query);
	
	List<Project> findByProgramId(Long programId);
	
	List<Project> findByTypeIgnoreCase(String type);

	public List<Project> findByStatusNotInIgnoreCase(List<String> statusList);
	
	public List<Project> findByIdInAndStatusIgnoreCase(Set<Long> id,String status);
	
	List<Project> findByNameIgnoreCaseContaining(String name);
}
