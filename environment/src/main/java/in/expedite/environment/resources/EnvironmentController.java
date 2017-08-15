package in.expedite.environment.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.expedite.environment.entity.Environment;
import in.expedite.environment.service.EnvironmentService;
import in.expedite.environment.utills.ExJsonResponse;


@RestController
@RequestMapping("/resource/delivery")
public class EnvironmentController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EnvironmentService envService;
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public List<Environment> getEnvironments(){
		return envService.getEnvironments();
	}
	
	/**
	 * edit and add environment
	 * @param env
	 * @return
	 */
	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public Environment saveEnvironments(@RequestBody Environment env){
		return envService.save(env);
	}
	
	
	
}
