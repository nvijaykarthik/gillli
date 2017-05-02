package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.entity.AccessCode;

public interface AccessCodeRepository extends JpaRepository<AccessCode, Long>{

	public AccessCode findByMappingAndMethod(String mapping,String method);
	
}
