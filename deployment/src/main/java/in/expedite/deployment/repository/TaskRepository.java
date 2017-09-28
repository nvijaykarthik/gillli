package in.expedite.deployment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import in.expedite.deployment.entity.Deployment;
import in.expedite.deployment.entity.Task;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long>,JpaSpecificationExecutor<Task> {

	List<Task> findByDepolymentIdOrderBySequenceAsc(Long depolymentId);
}
