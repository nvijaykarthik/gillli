package in.expedite.core.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.core.entity.Application;

public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {
	
	List<Application> findByTeamId(Long teamId);

}
