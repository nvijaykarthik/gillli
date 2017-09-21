package in.expedite.deployment.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.deployment.entity.Deployment;



public interface DeploymentRepository extends PagingAndSortingRepository<Deployment, Long>,JpaSpecificationExecutor<Deployment> {

}
