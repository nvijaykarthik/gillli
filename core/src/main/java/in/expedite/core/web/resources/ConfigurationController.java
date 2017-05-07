package in.expedite.core.web.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import in.expedite.core.entity.Configuration;
import in.expedite.core.service.ConfigurationService;
import in.expedite.core.utils.ExJsonResponse;



@RestController
@RequestMapping("/resource/config")
public class ConfigurationController { 

	@Autowired
	ConfigurationService cs;
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationController.class);
	
	@RequestMapping(produces="application/json",method=RequestMethod.GET)
	public Iterable<Configuration> getConfiguration(@RequestParam(name="p") Integer pageNumber,
			@RequestParam(name="key",required=false) String key,
			@RequestParam(name="value",required=false) String value) throws Exception{
		LOG.info("Retrieving All configuration");
		Iterable<Configuration> confList=cs.getAllConfiguration(pageNumber,key,value);
		LOG.debug("Getting All the configuration available "+confList);
		return  confList;
	}
	
	@RequestMapping(produces="application/json",method=RequestMethod.POST)
	public ExJsonResponse addConfig(@Valid @RequestBody Configuration cfg,@RequestHeader(required=false,name="username") String username){
		LOG.info("Adding new configuration property" + cfg.toString());
		cs.saveConfiguration(cfg,username);
    	return new ExJsonResponse(0,"Sucessfully Added");
	}
	
	@RequestMapping(produces="application/json",method=RequestMethod.PATCH)
	public ExJsonResponse saveConfig(@Valid @RequestBody Configuration cfg,@RequestHeader(required=false,name="username") String username)	{
		LOG.info("Updating existing configuration property" + cfg.toString());
		cs.updateConfiguration(cfg,username);
		return new ExJsonResponse(0,"Sucessfully Updated");
	}
	
	@RequestMapping(path="/mappings",produces="application/json",method=RequestMethod.GET)
	public Set<RequestMappingInfo>  getMappings()	{
		//RequestMappingInfo 
		List<PatternsRequestCondition> pattern=new ArrayList<>();
		for(RequestMappingInfo info:handlerMapping.getHandlerMethods().keySet()){
			pattern.add(info.getPatternsCondition());
		}
		LOG.info("List of mappings:" + pattern);
		return handlerMapping.getHandlerMethods().keySet();
	}
}
