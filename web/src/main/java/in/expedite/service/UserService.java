package in.expedite.service;

import java.util.List;

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

import in.expedite.entity.Role;
import in.expedite.entity.RoleAccessXref;
import in.expedite.entity.State;
import in.expedite.entity.User;
import in.expedite.entity.UserRole;
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
	
	@Value("${expedite.page.size}")
	private Integer pageSize;

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
	public User deActivate(User user) {
		log.debug("Deactivating user " + user);
		user.setState(State.INACTIVE.toString());
		return userRepository.save(user);
	}

	/**
	 * Resetting password
	 * 
	 * @param userId
	 * @return
	 */
	public void resetPassword(String userId) {
		log.debug("Resetting Password " + userId);
		userDao.setPasswordForUser(userId,encoder.encode("password"));
	}

	
	/**
	 * Updating password
	 * 
	 * @param userId
	 * @return
	 */
	public void updatePassword(String userId,String password) {
		log.debug("Resetting Password " + userId);
		userDao.setPasswordForUser(userId,encoder.encode(password));
	}
	/**
	 * Updating User
	 * 
	 * @param user
	 * @return
	 */
	public User updateUser(User user) {
		log.debug("Updating user " + user);
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
	
	public UserRole addUserRole(UserRole userRoles){
		log.debug("Added User Role");
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

}
