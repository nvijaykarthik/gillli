package in.expedite.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserServiceDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void setPasswordForUser(String userId,String password){
			Query query= em.createNativeQuery("update user set password=:password where user_id=:userid");
			query.setParameter("userid", userId);
			query.setParameter("password", password);
			query.executeUpdate();
	};
	
}
