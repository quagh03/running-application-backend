package runningEvent.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemberId", nullable = false, unique = true)
    private int memberId;

    @Column(name = "Username", unique = true)
    @JsonProperty("username")
    private String username;

    @Column(name = "Password")
    private String password;

    @JsonProperty("firstname")
    @Column(name = "Firstname")
    private String firstname;

    @JsonProperty("lastname")
    @Column(name = "Lastname")
    private String lastname;

    @JsonProperty("city")
    @Column(name = "City")
    private String city;

    @JsonProperty("id")
    @Column(name = "strava_id", unique = true)
    private Long stravaId;

    @JsonProperty("sex")
    @Column(name = "Sex")
    private char sex;

    @JsonProperty("profile")
    @Column(name = "Profile")
    private String profile;

    public Members(String username, String password, String firstname, String lastname, String city, Long stravaId, char sex, String profile) {
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

    @Override
    public String toString() {
        return "Member{" +
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
