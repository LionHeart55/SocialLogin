package zone.cognitive.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zone.cognitive.dto.LocalUser;
import zone.cognitive.model.User;
//import zone.cognitive.service.UserDAO;
import zone.cognitive.service.UserService;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
@Service("localUserDetailService")
public class LocalUserDetailService implements UserDetailsService 
{
    @Autowired
    public UserService userService;

    @Override
    @Transactional
    public LocalUser loadUserByUsername(final String userId) throws UsernameNotFoundException {
        User user = userService.getByUsername(userId); // get
        if (user == null) {
            return null;
        }
        
        return LocalUser.createLocalUser(user);
    }
}
