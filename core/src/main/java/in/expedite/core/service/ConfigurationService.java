package in.expedite.core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.Configuration;
import in.expedite.core.repository.ConfigurationRepository;
import in.expedite.core.specification.SpecificationUtils;

@Service
@Transactional
public class ConfigurationService {

	@Autowired
	ConfigurationRepository configurationRepository;
	
	@Value("${expedite.page.size}")
	private Integer pageSize;
	
	/**
	 * Find the required configuration
	 * @param id
	 * @return
	 */
	public Configuration getConfiguration(Integer id){
		return configurationRepository.findOne(id);  
	}
	
	/**
	 * Get all the available configuration 
	 * @return
	 */
	public Iterable<Configuration> getAllConfiguration(){
		return configurationRepository.findAll(); 
	}
	
	/**
	 * Get all the available configuration Pagable
	 * @return
	 */
	public Iterable<Configuration> getAllConfiguration(Integer pageNumber,String key,String value){
		PageRequest pg= new PageRequest(pageNumber, pageSize);
		Specification<Configuration> spec= SpecificationUtils.getConfigurationSpecs(key, value);
		return configurationRepository.findAll(spec,pg); 
	}
	
	/**
	 * Save and update the configuration
	 * @param conf
	 * @return
	 */
	public Configuration saveConfiguration(Configuration conf){
		return configurationRepository.save(conf);
	}
	
	/**
	 * Get the number of configuration 
	 * @return
	 */
	public Long getConfigCount(){
		return configurationRepository.count();
	}
	
}
