package in.expedite.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.project.entity.Estimates;

public interface EstimatesRepository extends JpaRepository<Estimates, Long>{

	List<Estimates> findByProjectIdAndTeamId(Long projId, Long teamId);

	List<Estimates> findByProjectId(Long projId);

	List<Estimates> findByTeamId(Long teamId);

}
