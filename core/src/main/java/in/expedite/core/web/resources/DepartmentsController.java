package in.expedite.core.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.core.entity.Department;
import in.expedite.core.service.DepartmentsService;
import in.expedite.core.utils.ExJsonResponse;


@RestController
@RequestMapping("/resource/departments")
public class DepartmentsController {

	@Autowired
	private DepartmentsService departmentService;
	
	
	@RequestMapping(path="/pagable",method=RequestMethod.GET,produces="application/json")
	public Iterable<Department> getDepartments(@RequestParam("p") Integer pageNo,@RequestParam(required=false) String departmentName,@RequestParam(required=false) String manager){
		return departmentService.getDepartments(pageNo, departmentName, manager);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public Iterable<Department> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json",path="/search")
	public Iterable<Department> getDepartmentsByName(@RequestParam(required=false,name="s") String departmentName){
		return departmentService.getDepartmentsByName(departmentName);
	}
	
	
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public ExJsonResponse addDepartment(@RequestBody Department deps,@RequestAttribute(required=false,name="username") String username){
		departmentService.addDepartment(deps,username);
		return new ExJsonResponse(0,"Sucessfully Added");
	}

	
	
	@RequestMapping(path="/deptById",method=RequestMethod.GET,produces="application/json")
	public Department getDeptById(@RequestParam Long deptId){
    	return departmentService.getDeptById(deptId);
	}
	
	
	
}
