package in.expedite.email.DAO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSupportDAO {

	@Autowired
	EntityManager em;
	
	public String getValue(String key) {
		String sql="select value_str from APP_CONFIGURATION where CONFIG_KEY=:key";
		Query query=em.createNativeQuery(sql);
		query.setParameter("key", key);
		Object value=query.getSingleResult();
		return String.valueOf(value);
	}
	

	public List<String> getTeamEmail(Long teamId){
		String sql = "select u.email from team_member tm,user u where tm.team_id=:teamId and tm.user_id=u.user_id";
		Query query=em.createNativeQuery(sql);
		query.setParameter("teamId", teamId);
		List<Object> emails=query.getResultList();
		return emails
				.stream()
				.map(object -> Objects.toString(object, null))
				.collect(Collectors.toList());
	}
}
