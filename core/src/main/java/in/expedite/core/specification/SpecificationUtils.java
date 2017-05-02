package in.expedite.core.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import in.expedite.core.entity.Configuration;
import in.expedite.core.entity.Department;


public class SpecificationUtils {

	private SpecificationUtils() {
	}

	public static Specification<Configuration> getConfigurationSpecs(String key, String value) {

		return new Specification<Configuration>() {
			@Override
			public Predicate toPredicate(Root<Configuration> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (!StringUtils.isEmpty(key)) {
					predicates.add(cb.like(cb.lower(root.get("key")), "%" + key.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(value)) {
					predicates.add(cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%"));
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};

	}
	
	public static Specification<Department> getDepartmentsSpecs(String departmentName, String manager) {

		return new Specification<Department>(){

			@Override
			public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (!StringUtils.isEmpty(departmentName)) {
					predicates.add(cb.like(cb.lower(root.get("departmentName")), "%" + departmentName.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(manager)) {
					final Predicate mngrPredicate = cb.like(root.join("manager").get("userId"),"%" + manager.toLowerCase() + "%");
					predicates.add(mngrPredicate);
				}
					return cb.and(predicates.toArray(new Predicate[] {}));
			}
			
		};
	}
}
