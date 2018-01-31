package zone.cognitive.service;

import java.util.List;

import zone.cognitive.model.Role;

public interface RoleService {
	Role getById(Long id);
	List<Role> getAll();
	List<Role> getAllByIds(String ids);
	Role create(Role role);
	Role update(Role role);
	void delete(Role role);
	void deleteById(Long id);
}
