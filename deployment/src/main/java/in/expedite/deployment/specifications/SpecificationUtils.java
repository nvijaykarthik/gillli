package in.expedite.deployment.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import in.expedite.deployment.entity.Deployment;

public class SpecificationUtils {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private SpecificationUtils() {
	}

	public static Specification<Deployment> getDeploymentSpecs(Long id, String title, String status,
			String projectReference, String createdBy, Long teamId) {

		return new Specification<Deployment>() {
			@Override
			public Predicate toPredicate(Root<Deployment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				if (null != id) {
					predicates.add(cb.equal(cb.lower(root.get("id")), id));
				}
				if (!StringUtils.isEmpty(title)) {
					predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(status)) {
					predicates.add(cb.like(cb.lower(root.get("status")), "%" + status.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(projectReference)) {
					predicates.add(cb.like(cb.lower(root.get("projectReference")),
							"%" + projectReference.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(createdBy)) {
					predicates.add(cb.like(cb.lower(root.get("createdBy")), "%" + createdBy.toLowerCase() + "%"));
				}
				if (null != teamId) {
					predicates.add(cb.equal(cb.lower(root.get("forTeamId")), teamId));
				}

				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};

	}
}
