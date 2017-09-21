package in.expedite.deployment.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import in.expedite.deployment.entity.Deployment;
import in.expedite.deployment.repository.DeploymentRepository;
import in.expedite.deployment.specifications.SpecificationUtils;

@Service
public class DeploymentService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeploymentRepository deploymentRepo;

	@Value("${expedite.page.size}")
	private Integer pageSize;

	public Iterable<Deployment> findDeployments(Long id, String title, String status, String projectReference,
			String createdBy, Long teamId) throws Exception {
		
		if (null == teamId && null == id && StringUtils.isBlank(title) && StringUtils.isBlank(status)
				&& StringUtils.isBlank(projectReference) && StringUtils.isBlank(createdBy)) {
			throw new Exception("Please specify atleast one field to initiate Search");
		}
		
		Specification<Deployment> spec = SpecificationUtils.getDeploymentSpecs(id, title, status, projectReference,
				createdBy, teamId);
		return deploymentRepo.findAll(spec);
	}

	public Deployment getDeploymentById(Long id) {
		log.info("Incomming data id:{}",id);
		return deploymentRepo.findOne(id);
	}
}
