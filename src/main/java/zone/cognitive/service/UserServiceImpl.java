package zone.cognitive.service;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zone.cognitive.dto.LocalUser;
import zone.cognitive.dto.UserRegistrationForm;
import zone.cognitive.model.Role;
import zone.cognitive.model.User;
import zone.cognitive.persist.Repository;
import zone.cognitive.security.service.UserAlreadyExistAuthenticationException;
import zone.cognitive.service.UserService;

/**
 * Created by shocron on 11.28.2017.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	final static protected Log logger = LogFactory.getLog(UserServiceImpl.class.getName());
	
	@Autowired
    private Repository<User> userRepo;
	
	@Autowired
    public Repository<Role> roleRepo;
    
	
	@Transactional
    public LocalUser loadUserByUsername(final String userId) throws UsernameNotFoundException 
	{
        User user = getByUsername(userId);
        if (user == null) {
            return null;
        }
        
        return LocalUser.createLocalUser(user);
    }
	
    @Transactional(value = "transactionManager")
    public LocalUser registerNewUser(final UserRegistrationForm userRegistrationForm) throws UserAlreadyExistAuthenticationException {

    	logger.debug("UserRegistrationForm  UserRegistrationForm: " + userRegistrationForm);

        User userExist = getByUsername(userRegistrationForm.getUserId()); //get()
        if (userExist != null) {
        	logger.debug("UserRegistrationForm  throwing exception user exist: " + userExist.getName());
        	return LocalUser.createLocalUser(userExist);
        	//throw new UserAlreadyExistAuthenticationException("User with email id " + userRegistrationForm.getEmail() + " already exist");
        }

        User user = buildUser(userRegistrationForm);
        user = create(user);
        return LocalUser.createLocalUser(user);
        //return (LocalUser) userDetailService.loadUserByUsername(userRegistrationForm.getUserId());
    }

    private User buildUser(final UserRegistrationForm formDTO) {
        User user = new User();
        user.setUserId(formDTO.getUserId());
        user.setEmailId(formDTO.getEmail());
        user.setName(formDTO.getFirstName() + " " + formDTO.getLastName());
        user.setPassword(formDTO.getPassword());
        user.setActive(1);
        user.setProvider(formDTO.getSocialProvider().name());
        
        Role role = roleRepo.loadById(Role.class, 1L);
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        
        return user;
    }
	
	@Transactional(readOnly = true)
	public User getByUsername(String username) {
		logger.debug("Get User by username: "+username+" service method");
		
		return userRepo.loadOneByFilter(User.class,"userId='"+username+"'");
	}
	
	@Transactional(readOnly = true)
	public User getById(Long id) {
		logger.debug("Get User by id: "+id+" service method");
		
		return userRepo.read(id);
	}
	
	@Transactional(readOnly = true)
	public User getById(String userId) {
		logger.debug("Get User by user Id: "+userId+" service method");
		
		return userRepo.loadOneByFilter(User.class,"userId="+userId);
	}
	
	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userRepo.loadAll(User.class);
	}
	
	@Transactional(readOnly = true)
	public List<User> getAllByIds(String ids) {
		logger.debug("Get User by IDs: "+ids+" service method");
		
		return userRepo.loadAllIn(User.class,ids);
	}

	@Transactional //(propagation = Propagation.REQUIRED)
	public User create(User user) {
		logger.debug("Create user service method");
		
		userRepo.create(user);
		return user;
	}
	
	@Transactional
	public User update(User user) {
		logger.debug("Update User service method");
		
		userRepo.update(user);
		return user;
	}

    public void delete(User user) {
		logger.debug("Delete User service method");
		
		userRepo.delete(user);
	}
	
    public void deleteById(Long id) {
		logger.debug("Delete User by id: "+id+" service method");
		
		userRepo.delete(id);
	}

	public Repository<User> getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(Repository<User> userRepo) {
		this.userRepo = userRepo;
	}

	public Repository<Role> getRoleRepo() {
		return roleRepo;
	}

	public void setRoleRepo(Repository<Role> roleRepo) {
		this.roleRepo = roleRepo;
	}
    
}