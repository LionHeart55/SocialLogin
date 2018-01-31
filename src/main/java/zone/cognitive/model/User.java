package zone.cognitive.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = -2647543435900679498L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name = "id", unique = true, nullable = false)
	private Long Id;
	
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "name", nullable = true, length = 32)
    private String name;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "email_id", nullable = true, length = 128)
    private String emailId;

    @Column(name = "active", nullable = false, length = 1)
    private Integer active;

    @Column(name = "provider", nullable = false, length = 32)
    private String provider;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(final String userId, final String name, final String password, final String emailId, final Integer active, final String provider, final Set<Role> roles) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.emailId = emailId;
        this.active = active;
        this.provider = provider;
        this.roles = roles;
    }

    public Long getId() {
		return Id;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(final Integer active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }
}
