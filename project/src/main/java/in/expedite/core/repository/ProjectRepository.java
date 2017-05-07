package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.core.entity.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project,Long>,JpaSpecificationExecutor<Project>{

}
