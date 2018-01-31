package zone.cognitive.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zone.cognitive.model.Role;
import zone.cognitive.persist.Repository;
import zone.cognitive.service.RoleService;

/**
 * Created by shocron on 11.28.2017.
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	final static protected Log logger = LogFactory.getLog(RoleServiceImpl.class.getName());
	
	@Autowired
    private Repository<Role> roleRepo;
	
	
	@Transactional(readOnly = true)
	public Role getById(Long id) {
		logger.debug("Get Role by id: "+id+" service method");
		
		return roleRepo.read(id);
	}
	
	@Transactional(readOnly = true)
	public List<Role> getAll() {
		return roleRepo.loadAll(Role.class);
	}
	
	@Transactional(readOnly = true)
	public List<Role> getAllByIds(String ids) {
		logger.debug("Get Role by IDs: "+ids+" service method");
		
		return roleRepo.loadAllIn(Role.class,ids);
	}

	@Transactional //(propagation = Propagation.REQUIRED)
	public Role create(Role role) {
		logger.debug("Create Role service method");
		
		roleRepo.create(role);
		return role;
	}
	
	@Transactional
	public Role update(Role role) {
		logger.debug("Update Role service method");
		
		roleRepo.update(role);
		return role;
	}

    public void delete(Role role) {
		logger.debug("Delete Role service method");
		
		roleRepo.delete(role);
	}
	
    public void deleteById(Long id) {
		logger.debug("Delete Role by id: "+id+" service method");
		
		roleRepo.delete(id);
	}
}