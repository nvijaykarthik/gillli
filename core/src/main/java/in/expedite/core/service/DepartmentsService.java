package in.expedite.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import in.expedite.core.entity.Department;
import in.expedite.core.repository.DepartmentsRepository;
import in.expedite.core.specification.SpecificationUtils;

@Service
public class DepartmentsService {

	@Value("${expedite.page.size}")
	private Integer pageSize;
	
	@Autowired
	DepartmentsRepository departmentRepo;
	
	public Iterable<Department> getDepartments(@RequestParam("p") Integer pageNo,
			@RequestParam String departmentName,@RequestParam String manager){
		
		PageRequest pg = new PageRequest(pageNo, pageSize);
		Specification<Department> spec = SpecificationUtils.getDepartmentsSpecs(departmentName, manager);
		
		return departmentRepo.findAll(spec, pg);
	}
	
	public Department addDepartment(Department dept,String username){
		dept.setCreatedBy(username);
		dept.setModifiedBy(username);
		dept.setCreatedDate(new Date());
		dept.setModifiedDate(new Date());
		return departmentRepo.save(dept);
	}
	
	
	public Iterable<Department> getAllDepartments(){
		return departmentRepo.findAll();
	}
	
	public Iterable<Department> getDepartmentsByName(String deptName){
		return departmentRepo.findByDepartmentNameContainingIgnoreCase(deptName);
	}

	public Department getDeptById(Long deptId) {
		return departmentRepo.findById(deptId);
	}

	public List<Department> getDeptByManager(String username) {
		 return departmentRepo.findByManagerId(username);
	}
	
	
}
