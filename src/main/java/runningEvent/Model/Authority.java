package runningEvent.Model;

import javax.persistence.*;


@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private Members members;

    public Authority() {
    }

    public Authority(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public Authority(String username, Role role, Members members) {
        this.username = username;
        this.role = role;
        this.members = members;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public enum Role{
        ROLE_MEMBER,
        ROLE_ADMIN
    }
}
