package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	

}
