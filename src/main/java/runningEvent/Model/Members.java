package runningEvent.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "members")
public class Members{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, unique = true)
    private int memberId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "city")
    private String city;

    @Column(name = "strava_id", unique = true)
    private Long stravaId;

    @Column(name = "sex")
    private char sex;

    @Column(name = "user_profile")
    private String profile;

    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
    private List<Authority> authorities;

    public Members(int memberId, String username, String password, String firstname, String lastname, String city, Long stravaId, char sex, String profile, List<Activities> activities, List<EventSession> eventSessions) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.stravaId = stravaId;
        this.sex = sex;
        this.profile = profile;
    }

    public Members(String firstname, String lastname, String city, Long stravaId, char sex, String profile) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.stravaId = stravaId;
        this.sex = sex;
        this.profile = profile;
    }

    public Members(int memberId, String username, String password, String firstname, String lastname, String city, Long stravaId, char sex, String profile, List<Authority> authorities) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.stravaId = stravaId;
        this.sex = sex;
        this.profile = profile;
        this.authorities = authorities;
    }

    public Members() {
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Members{" +
                "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", stravaId=" + stravaId +
                ", sex=" + sex +
                ", profile='" + profile + '\'' +
                '}';
    }
}
