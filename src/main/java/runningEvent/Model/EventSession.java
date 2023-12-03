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
    private Integer sessionId;

    @Column(name = "event_id")
    private Integer eventId;

    @ManyToOne
    @JoinColumn(name = "activities_id")
    private Activities activities;

    public EventSession() {
    }

    public EventSession(Integer sessionId, Integer eventId, Activities activities) {
        this.sessionId = sessionId;
        this.eventId = eventId;
        this.activities = activities;
    }

    public EventSession(Integer eventId, Integer activityId) {
        this.eventId = eventId;
//        this.activityId = activityId;
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

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }
}
