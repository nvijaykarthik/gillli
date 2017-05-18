package in.expedite.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import in.expedite.core.entity.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project,Long>,JpaSpecificationExecutor<Project>{

	@Query("select p from Project p where lower(p.name) like %:query% or p.id like %:query%")
	public List<Project> searchProject(@Param(value = "query") String query);
}
