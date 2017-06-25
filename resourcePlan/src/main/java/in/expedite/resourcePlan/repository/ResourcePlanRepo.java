package in.expedite.resourcePlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.resourcePlan.entity.ResourcePlan;

public interface ResourcePlanRepo extends JpaRepository<ResourcePlan, Long> {

}
