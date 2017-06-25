package in.expedite.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.resource.entity.Resource;

public interface ResourceRepo extends JpaRepository<Resource, Long> {

}
