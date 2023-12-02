package runningEvent.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "running_events")
@JsonInclude(JsonInclude.Include.NON_NULL) // This will ignore all null fields
public class RunningEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "participant")
    @JsonProperty("participant")
    private Integer participant;

    @Column(name = "total_distance", precision = 10, scale = 2)
    private BigDecimal total_distance;


    public RunningEvent() {
    }

    public RunningEvent(Integer eventId, String eventName, Date startDate, Date endDate, Integer participant, BigDecimal total_distance) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participant = participant;
        this.total_distance = total_distance;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public BigDecimal getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(BigDecimal total_distance) {
        this.total_distance = total_distance;
    }

    @Override
    public String toString() {
        return "RunningEvent{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participant=" + participant +
                ", total_distance=" + total_distance +
                '}';
    }
}
