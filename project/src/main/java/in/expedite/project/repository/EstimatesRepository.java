package in.expedite.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.project.entity.Estimates;

public interface EstimatesRepository extends JpaRepository<Estimates, Long>{

}
