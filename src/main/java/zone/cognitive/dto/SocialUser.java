package zone.cognitive.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
public class SocialUser extends org.springframework.social.security.SocialUser {

	private static final long serialVersionUID = -2289874758044695802L;
	
	private Long id;
    private String userId;

    public SocialUser(final Long Id, final String userId, final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [SocialUser] - ");
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
