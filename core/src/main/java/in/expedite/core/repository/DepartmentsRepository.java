package in.expedite.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.core.entity.Department;

public interface DepartmentsRepository extends PagingAndSortingRepository<Department, Integer>,JpaSpecificationExecutor<Department>{

	public Iterable<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);
	
	public Department findById(Long id);
	
	public List<Department> findByManagerId(String managerId);
}
