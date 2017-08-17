package in.expedite.environment.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.expedite.environment.entity.Environment;
import in.expedite.environment.entity.EnvironmentDetails;
import in.expedite.environment.repository.EnvironmentDetailsRepo;
import in.expedite.environment.repository.EnvironmentRepository;

@Service
public class EnvironmentService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private EnvironmentRepository envRepo; 
	
	@Autowired
	private EnvironmentDetailsRepo envDetRepo;
	
	@Value("${expedite.file.repo.path}")
	private String repoHome;
	
	
	public List<Environment> getEnvironments(){
		List<Environment> env= envRepo.findAll();
		return env;
	}

	
	public Environment save(Environment env,String username){
		env.setCreatedBy(username);
		return envRepo.save(env);
	}
	
	
	public EnvironmentDetails getDetails(Long appId, Long envId) {
		return envDetRepo.findByApplicationIdAndEnvironmentId(appId,envId);
	}


	public EnvironmentDetails save(EnvironmentDetails env,String username) {
		env.setCreatedBy(username);
		return envDetRepo.save(env);
	}
}
