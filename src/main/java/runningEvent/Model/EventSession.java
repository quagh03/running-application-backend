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

    @ManyToOne
    @JoinColumn(name = "event_id")
    private RunningEvent event;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members members;

    @ManyToOne
    @JoinColumn(name = "activities_id")
    private Activities activity;

    @Column(name = "session_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionDate;

    @Column(name = "distance", precision = 10, scale = 2)
    private BigDecimal distance;

    public EventSession(int sessionId, RunningEvent event, Members members, Activities activity, Date sessionDate, BigDecimal distance) {
        this.sessionId = sessionId;
        this.event = event;
        this.members = members;
        this.activity = activity;
        this.sessionDate = sessionDate;
        this.distance = distance;
    }

    public EventSession() {
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public RunningEvent getEvent() {
        return event;
    }

    public void setEvent(RunningEvent event) {
        this.event = event;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public Activities getActivity() {
        return activity;
    }

    public void setActivity(Activities activity) {
        this.activity = activity;
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
                ", event=" + event +
                ", members=" + members +
                ", activity=" + activity +
                ", sessionDate=" + sessionDate +
                ", distance=" + distance +
                '}';
    }
}
