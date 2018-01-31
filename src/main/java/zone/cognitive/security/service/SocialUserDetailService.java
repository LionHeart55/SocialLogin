package zone.cognitive.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import zone.cognitive.dto.LocalUser;
import zone.cognitive.dto.SocialUser;
import zone.cognitive.service.UserService;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
@Service("socialUserDetailService")
public class SocialUserDetailService implements SocialUserDetailsService {

    @Autowired // @Qualifier(value = "localUserDetailService")
    private UserService userService;

    @Override
    public SocialUserDetails loadUserByUserId(final String userId) throws UsernameNotFoundException, DataAccessException {
        LocalUser user = (LocalUser) userService.loadUserByUsername(userId);
        if (user == null) {
            throw new SocialAuthenticationException("No local user mapped with social user " + userId);
        }
        return new SocialUser(user.getId(), user.getUserId(),user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
    }
}
