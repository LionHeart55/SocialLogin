package zone.cognitive.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import zone.cognitive.model.Role;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
public class LocalUser extends User {

	private static final long serialVersionUID = 8205018831234564433L;

	private Long id;
	private String userId;
	
    public LocalUser(final Long Id, final String userId, final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = Id;
        this.userId = userId;
    }

    public Long getId() {
		return id;
	}

	public String getUserId() {
        return userId;
    }
	
	public static LocalUser createLocalUser(zone.cognitive.model.User user) 
	{
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = LocalUser.buildSimpleGrantedAuthorities(user);
        
		//TODO: is it really name or username??
		return new LocalUser(user.getId(), user.getUserId(), user.getName(), user.getPassword(), user.getActive() == 1 ? true : false, 
				true, true, true, simpleGrantedAuthorities);
	}
	
	private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final zone.cognitive.model.User user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return simpleGrantedAuthorities;
    }
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [LocalUser] - ");
        sb.append("id: ").append(getId()).append("; ");
        sb.append("userId: ").append(getUserId()).append("; ");
        sb.append("Username: ").append(getUsername()).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.isEnabled()).append("; ");
        sb.append("AccountNonExpired: ").append(this.isAccountNonExpired()).append("; ");
        sb.append("credentialsNonExpired: ").append(this.isCredentialsNonExpired()).append("; ");
        sb.append("AccountNonLocked: ").append(this.isAccountNonLocked()).append("; ");

        if (!this.getAuthorities().isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : this.getAuthorities()) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}
