package modules.organizer;

import modules.AbstractEntity;
import modules.event.EventEntity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by adric on 10/10/2016.
 */
@Entity
@Table(name = "ORGANIZER")
public class OrganizerEntity extends AbstractEntity {

    @Id
    @Column(name = "mail")
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "company", nullable = false)
    private String company;

    @OneToMany(mappedBy="organizer_id", fetch = FetchType.LAZY)
    private Collection<EventEntity> events;

    public OrganizerEntity(String mail, String password, String last_name, String first_name, String company, Collection<EventEntity> events) {
        this.mail = mail;
        this.password = password;
        this.last_name = last_name;
        this.first_name = first_name;
        this.company = company;
        this.events = events;
    }

    public OrganizerEntity() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "OrganizerEntity{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
