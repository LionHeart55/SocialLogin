package zone.cognitive.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import zone.cognitive.dto.LocalUser;
import zone.cognitive.dto.UserRegistrationForm;
import zone.cognitive.model.User;
import zone.cognitive.security.service.UserAlreadyExistAuthenticationException;

public interface UserService {
	LocalUser loadUserByUsername(final String userId) throws UsernameNotFoundException;
	UserDetails registerNewUser(UserRegistrationForm UserRegistrationForm)
			throws UserAlreadyExistAuthenticationException;
	User getByUsername(String username);
	User getById(Long id);
	User getById(String userId);
	List<User> getAll();
	List<User> getAllByIds(String ids);
	User create(User user);
	User update(User user);
	void delete(User user);
	void deleteById(Long id);
}
