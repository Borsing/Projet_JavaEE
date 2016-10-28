package modules.participant;

import modules.AbstractEntity;
import modules.event.EventEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by adric on 10/10/2016.
 */
@Entity
@Table(name = "PARTICIPANT")
public class ParticipantEntity extends AbstractEntity{

    @Id
    @Column(name = "mail")
    private String mail;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "company", nullable = false)
    private String company;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private Collection<EventEntity> events;

    public ParticipantEntity(String mail, String last_name, String first_name, String company, Collection<EventEntity> events) {
        this.mail = mail;
        this.last_name = last_name;
        this.first_name = first_name;
        this.company = company;
        this.events = events;
    }

    public ParticipantEntity() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Collection<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(Collection<EventEntity> events) {
        this.events = events;
    }
    
	@Override
	public Object getId() {
		return this.getMail();
	}

    @Override
    public String toString() {
    	Collection<String> eventsString = new ArrayList<>();
    	for (EventEntity e : events){
    		eventsString.add(e.getName());
    	}    	
    	
        return "ParticipantEntity{" +
                "mail='" + mail + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", company='" + company + '\'' +
               ", events='" +  eventsString +
                "}";
    }
}
