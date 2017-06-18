package in.expedite.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.expedite.core.entity.ReferenceProjects;

public interface ReferenceProjectRepo extends JpaRepository<ReferenceProjects, Long> {

	@Query(nativeQuery=true,value="Select rp.id as rpId,p.id as pId ,p.name as name,p.status as sts,rp.dependency_mode as depMode from project p,reference_projects rp where rp.reference_id=p.id and rp.project_id=:projectId")
	List<Object[]> findRefForProject(@Param(value = "projectId")  Long projectId);

	
}
