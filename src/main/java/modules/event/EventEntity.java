package modules.event;

import modules.AbstractEntity;
import modules.organizer.OrganizerEntity;
import modules.participant.ParticipantEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by adric on 10/10/2016.
 */
@Entity
@Table(name = "EVENT")
public class EventEntity extends AbstractEntity{

    @Id
    @GeneratedValue()
    @Column(name="id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_date", nullable = false)
    private Date begin_date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = false)
    private Date end_date;

    @Column(name = "address",nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private OrganizerEntity organizer_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name="EVENT_PARTICIPANT",
            joinColumns=@JoinColumn(name="id"),
            inverseJoinColumns=@JoinColumn(name="mail") )
    private Collection<ParticipantEntity> participants;

    public EventEntity(String name, String description, Date begin_date, Date end_date, String address, OrganizerEntity organizer_id, Collection<ParticipantEntity> participants) {
        this.name = name;
        this.description = description;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.address = address;
        this.organizer_id = organizer_id;
        this.participants = participants;
    }

    public EventEntity() {
    }

    public Object getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrganizerEntity getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(OrganizerEntity organizer_id) {
        this.organizer_id = organizer_id;
    }

    public Collection<ParticipantEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<ParticipantEntity> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", begin_date=" + begin_date +
                ", end_date=" + end_date +
                ", address='" + address + '\'' +
                ", organizer_id=" + organizer_id +
                ", participants=" + participants +
                '}';
    }
}
