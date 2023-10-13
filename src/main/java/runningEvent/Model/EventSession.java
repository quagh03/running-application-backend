package runningEvent.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "eventsession")
public class EventSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int sessionId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "activities_id")
    private Integer activityId;

    @Column(name = "session_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionDate;

    @Column(name = "distance", precision = 10, scale = 2)
    private BigDecimal distance;

    public EventSession() {
    }

    public EventSession(int sessionId, Integer eventId, Integer memberId, Integer activityId, Date sessionDate, BigDecimal distance) {
        this.sessionId = sessionId;
        this.eventId = eventId;
        this.memberId = memberId;
        this.activityId = activityId;
        this.sessionDate = sessionDate;
        this.distance = distance;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "EventSession{" +
                "sessionId=" + sessionId +
                ", eventId=" + eventId +
                ", memberId=" + memberId +
                ", activityId=" + activityId +
                ", sessionDate=" + sessionDate +
                ", distance=" + distance +
                '}';
    }
}
