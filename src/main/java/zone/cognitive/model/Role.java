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
@Table(name = "role")
public class Role implements Serializable 
{
	private static final long serialVersionUID = 8863376992666434806L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(final Long roleId, final String name, final Set<User> users) {
        this.id = roleId;
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public void addUser(final User user)
    {
        this.users.add(user);
    }
}
