package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.core.entity.Team;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
