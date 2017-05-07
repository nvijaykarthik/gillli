package in.expedite.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.ProjectDocType;
import in.expedite.core.entity.ProjectType;
import in.expedite.core.repository.ProjectDocTypeRepository;
import in.expedite.core.repository.ProjectTypeRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectTypeRepository projectTypeRepository;
	
	@Autowired
	private ProjectDocTypeRepository projectDocTypeRepository;
	
	@Value("${expedite.page.size}")
	private Integer pageSize;
	
	public List<ProjectType> getAllProjectTypes() {
		return projectTypeRepository.findAll();
	}

	public List<ProjectDocType> getAllProjectDocTypes() {
		return projectDocTypeRepository.findAll();
	}
	
	
}
