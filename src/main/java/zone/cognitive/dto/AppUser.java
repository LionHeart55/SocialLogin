package zone.cognitive.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUser extends User {

	private static final long serialVersionUID = -6318709590685069434L;

	private Long id;
	private String userId;
	
	public AppUser(Long Id, String userId, String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
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
	
	public static AppUser buildAppUser(LocalUser user) 
	{
		return new AppUser(user.getId(), user.getUserId(), user.getUsername(), "LOCAL_PASSWORD", user.isEnabled(), 
				user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
	}
	
	public static AppUser buildAppUser(SocialUser user) 
	{
		return new AppUser(user.getId(), user.getUserId(), user.getUsername(), "SOCIAL_PASSWORD", user.isEnabled(), 
				user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [AppUser] - ");
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
