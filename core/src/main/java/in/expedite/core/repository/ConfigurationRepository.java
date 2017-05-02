package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.core.entity.Configuration;

public interface ConfigurationRepository extends PagingAndSortingRepository<Configuration, Integer>,JpaSpecificationExecutor<Configuration>{

}
