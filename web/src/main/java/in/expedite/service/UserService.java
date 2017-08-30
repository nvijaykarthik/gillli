package in.expedite.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.expedite.email.service.PassworManagmentService;
import in.expedite.entity.ResetPassword;
import in.expedite.entity.Role;
import in.expedite.entity.RoleAccessXref;
import in.expedite.entity.State;
import in.expedite.entity.User;
import in.expedite.entity.UserRole;
import in.expedite.repository.ResetPasswordRepo;
import in.expedite.repository.RoleAccessXrefRepository;
import in.expedite.repository.UserRepository;
import in.expedite.repository.UserRoleRepository;
import in.expedite.repository.UserServiceDAO;
import in.expedite.specification.SpecificationUtils;
import in.expedite.utils.CollectionUtil;

@Service
@Transactional
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepo;
	
	@Autowired
	RoleAccessXrefRepository roleAccessrepo;
	
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	@Qualifier("bcryptEncoder")
	PasswordEncoder encoder;

	@Autowired
	UserServiceDAO userDao;
	
	@Autowired
	ResetPasswordRepo restPasswordRepo;
	
	@Value("${gilli.domain.name}")
	private String domainName;
	
	@Value("${expedite.page.size}")
	private Integer pageSize;

	@Autowired
	private PassworManagmentService pwdservice;
	
	
	public final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
	
	/**
	 * Add new user
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		log.debug("Adding new user " + user);
		user.setPassword(encoder.encode("password"));
		userRepository.save(user);
	}

	/**
	 * get user by user id
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(String userId) {
		log.debug("Get user by user id " + userId);
		return userRepository.findUserIdQuery(userId);
	}

	/**
	 * Deactivating user
	 * 
	 * @param user
	 * @return
	 */
	public User deActivate(User user,String username) {
		log.debug("Deactivating user " + user);
		user.setState(State.INACTIVE.toString());
		user.setModifiedBy(username);
		user.setModifiedDate(new Date());
		return userRepository.save(user);
	}

	/**
	 * Resetting password
	 * 
	 * @param userId
	 * @return
	 */
	public void resetPassword(String userId,String username) {
		log.debug("Resetting Password " + userId);
		userDao.setPasswordForUser(userId,encoder.encode("password"),username,new Date());
	}

	
	/**
	 * Updating password
	 * 
	 * @param userId
	 * @return
	 */
	public void updatePassword(String userId,String password,String username) {
		log.debug("Resetting Password " + userId);
		userDao.setPasswordForUser(userId,encoder.encode(password),username,new Date());
	}
	/**
	 * Updating User
	 * 
	 * @param user
	 * @return
	 */
	public User updateUser(User user,String username) {
		log.debug("Updating user " + user);
		user.setCreatedBy(username);
		user.setModifiedBy(username);
		user.setCreatedDate(new Date());
		user.setModifiedDate(new Date());
		return userRepository.save(user);
	}

	/**
	 * get list of all users.
	 * 
	 * @return
	 */
	public Iterable<User> getUsers(Integer pageNumber) {
		log.debug("Getting all the Users");
		PageRequest pg = new PageRequest(pageNumber, pageSize);
		Iterable<User> users = userRepository.findAll(pg);
		// PageRequest request =
		// new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC,
		// "firstName");
		log.trace("User List :" + users);
		return users;
	}

	public Long getCount() {
		log.debug("Getting total Users");
		return userRepository.count();
	}

	public Iterable<User> searchUser(String userId, String firstName, String secondName, String email, String state,
			Integer pageNumber) {
		log.debug("Getting all the Users based on the values : " + userId + "," + firstName + "," + secondName + ","
				+ email + "," + state + "," + pageNumber);
		PageRequest pg = new PageRequest(pageNumber, pageSize);
		Specification<User> spec = SpecificationUtils.getUserSearchSpecs(userId, firstName, secondName, email, state);
		return userRepository.findAll(spec, pg);
	}
	
	public UserRole addUserRole(UserRole userRoles,String username){
		log.debug("Added User Role");
		userRoles.setCreatedBy(username);
		userRoles.setModifiedBy(username);
		userRoles.setCreatedDate(new Date());
		userRoles.setModifiedDate(new Date());
		return userRoleRepo.save(userRoles);
	}
	
	public void deleteUserRole(String userId,String roleCode){
		log.debug("Delete User Role");
		userRoleRepo.deleteByUserIdAndRoleCode(userId,roleCode);
	}

	public List<RoleAccessXref> getUserRoles(String userId){
		log.debug("getList of user roles");
		return roleAccessrepo.findByRoleCodeIn(CollectionUtil.getList(userRoleRepo.findByUserId(userId)));
	}
	
	public List<Role> getActiveRolesForUser(String userId){
		log.debug("Getting Access Role Reference ");
		List<UserRole> userRoles=userRoleRepo.findByUserId(userId);
		List<Role> roles=roleService.getRoles();
		roles.forEach(role->{
			userRoles.forEach(userRole->{
				if(userRole.getRoleCode().equals(role.getRoleCode())){
					role.setActive(true);
				}
			});
		});
		return roles;
	}
	
	public Iterable<User> findUserByName(String name){
		return userRepository.findByFirstNameContainingIgnoreCaseOrSecondNameContainingIgnoreCase(name,name);
	}
	
	
	public List<User> getMembersForTeam(Long teamId){
		return userRepository.getMembersForTeam(teamId);
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void forgotpassword(String loginId) throws Exception{
		User user=userRepository.findByUserId(loginId);
		if(null==user) {
			throw new Exception("User not found");
		}
		String id="GI"+UUID.randomUUID().toString();
		
		ResetPassword rp = new ResetPassword();
		rp.setLinkId(id);
		rp.setUserId(user.getUserId());
		restPasswordRepo.save(rp);
		//send mail as link
		String resetLink=domainName+"/resetpwd.html?resetId="+id;
		pwdservice.sendForgotPasswordMail(resetLink, user.getEmail());
		log.debug("Reset Link : "+resetLink);
	}

	
	public String verifyToken(String resetId) throws Exception {
		ResetPassword rp= restPasswordRepo.findOne(resetId);
		if(null!=rp) {
			Long createdTime= rp.getCreatedDate().getTime();
			Long nowTime=new Date().getTime();
			Long timeDiff= Math.abs(createdTime-nowTime);
			
			if(timeDiff>MILLIS_PER_DAY) {
				throw new Exception("Your link is expired . it is more than 24 hrs");
			}
		}else {
			throw new Exception("Your link is not available . it is either expired or not created");
		}
		return rp.getUserId();
	}
	
	public void deleteToken(String resetId) throws Exception {
		restPasswordRepo.delete(resetId);
	}

}
