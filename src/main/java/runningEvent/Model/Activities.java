package runningEvent.Model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "activities")
public class Activities implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activities_id")
    private int activitiesId;

    @Column(name = "activities_stravaid", nullable = false)
    private long activitiesStravaId;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "distance", precision = 10, scale = 2)
    private BigDecimal distance;

    @Column(name = "activities_type")
    private String activitiesType;

    @Column(name = "strava_id")
    private Long stravaId;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    public Activities() {
    }

    public Activities(int activitiesId, long activitiesStravaId, String activityName, BigDecimal distance, String activitiesType, Long stravaId, Date startDate, Members members) {
        this.activitiesId = activitiesId;
        this.activitiesStravaId = activitiesStravaId;
        this.activityName = activityName;
        this.distance = distance;
        this.activitiesType = activitiesType;
        this.stravaId = stravaId;
        this.startDate = startDate;
    }

    public Activities(int activitiesId, long activitiesStravaId, String activityName, BigDecimal distance, String activitiesType, Long stravaId, Date startDate) {
        this.activitiesId = activitiesId;
        this.activitiesStravaId = activitiesStravaId;
        this.activityName = activityName;
        this.distance = distance;
        this.activitiesType = activitiesType;
        this.stravaId = stravaId;
        this.startDate = startDate;
    }

    public int getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(int activitiesId) {
        this.activitiesId = activitiesId;
    }

    public long getActivitiesStravaId() {
        return activitiesStravaId;
    }

    public void setActivitiesStravaId(long activitiesStravaId) {
        this.activitiesStravaId = activitiesStravaId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getActivitiesType() {
        return activitiesType;
    }

    public void setActivitiesType(String activitiesType) {
        this.activitiesType = activitiesType;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Activites{" +
                "activitiesId=" + activitiesId +
                ", activitiesStravaId=" + activitiesStravaId +
                ", activityName='" + activityName + '\'' +
                ", distance=" + distance +
                ", activitiesType='" + activitiesType + '\'' +
                ", stravaId=" + stravaId +
                ", startDate=" + startDate +
                '}';
    }
}
