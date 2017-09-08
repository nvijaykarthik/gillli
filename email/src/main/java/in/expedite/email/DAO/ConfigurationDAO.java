package in.expedite.email.DAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigurationDAO {

	@Autowired
	EntityManager em;
	
	public String getValue(String key) {
		String sql="select value_str from APP_CONFIGURATION where CONFIG_KEY=:key";
		Query query=em.createNativeQuery(sql);
		query.setParameter("key", key);
		Object value=query.getSingleResult();
		return String.valueOf(value);
	}
}
