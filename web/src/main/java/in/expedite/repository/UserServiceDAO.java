package in.expedite.repository;

import java.time.temporal.Temporal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserServiceDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void setPasswordForUser(String userId,String password,String modfiedBy,Date modifiedDate){
			Query query= em.createNativeQuery("update user set password=:password,modified_by=:modfiedBy,modified_date=:modifiedDate where user_id=:userid");
			query.setParameter("userid", userId);
			query.setParameter("password", password);
			query.setParameter("modfiedBy", modfiedBy);
			query.setParameter("modifiedDate", modifiedDate,TemporalType.TIMESTAMP);
			query.executeUpdate();
	};
	
}
