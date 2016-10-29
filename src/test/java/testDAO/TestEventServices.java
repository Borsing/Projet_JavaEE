package testDAO;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modules.ArgumentsParser;
import modules.DatabaseManager;
import modules.event.EventEntity;
import modules.event.EventService;
import modules.organizer.OrganizerEntity;
import modules.organizer.OrganizerService;
import modules.participant.ParticipantEntity;
import modules.participant.ParticipantService;

public class TestEventServices {

	private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
	private EventService es ;
	private ParticipantService ps ;
	private OrganizerService os ;

	
	EventEntity event1;
	OrganizerEntity organizer1 ;
	
	@Before
	public void setUp() throws PersistenceException, ClassNotFoundException, ParseException{
		es = new EventService();
		os = new OrganizerService();
		ps = new ParticipantService();
		databaseManager.openEntityManagerFactory();
		databaseManager.populate();
		
		organizer1 = new OrganizerEntity("adrien.cadoret@gmail.com", "adrien", "Cadoret", "Adrien", "VIF", null);
		
		event1 = new EventEntity("Event de Adrien", "C'est l'événement d'Adrien", (Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/11:00"),(Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/18:00"), "Adresse d'Adrien", organizer1, new LinkedList<>());
		event1.setId(1);
	}
	
	@Test
	public void findById() {
		assertEquals(event1,es.findEventById(1));	
	}
	
	@Test
	public void findByName() {
		assertTrue(event1.equals(es.findEventByName("Event de Adrien")));	//is True for real
	}
	
	@Test
	public void dropByOrganizer() {
		os.register("azerty", "azdf", "afzsc", "fsd", "fsd");
		try {
			es.createEvent("yghjk", "xvcb", (Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/11:00"),(Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/18:00"), "qdvfdx", "azerty");
		} catch (Exception e) {
			fail();
		}
		assertEquals(os.findOrganizerById("azerty"),es.findEventByOrganizer("azerty").get(0).getOrganizer_id());

		ps.joinEvent((int)es.findEventByName("yghjk").get(0).getId(), "toto", "vxc", "vdx", "vxc");
		
		os.unregister("azerty");
		
		
		assertFalse(os.exists("azerty"));
		assertTrue(es.findEventByName("yghjk").size()==0);
		assertNull(ps.findById("toto"));
	}
	
	@Test
	public void removeEvent() {
		
		es.removeEvent(1);

		assertTrue(es.findEventByName("Event de Adrien").size()==0);

	}
	
	@Test
	public void dropByEvent() {
		os.register("azerty", "azdf", "afzsc", "fsd", "fsd");
		try {
			es.createEvent("yghjk", "xvcb", (Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/11:00"),(Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/18:00"), "qdvfdx", "azerty");
		} catch (Exception e) {
			fail();
		}
		assertEquals(os.findOrganizerById("azerty"),es.findEventByOrganizer("azerty").get(0).getOrganizer_id());
		int id = (int)es.findEventByName("yghjk").get(0).getId();
		
		ps.joinEvent(id, "toto", "vxc", "vdx", "vxc");
		
		es.removeEvent(id);

		
		assertTrue(es.findEventByName("yghjk").size()==0);
		assertNull(ps.findById("toto"));
	}

	@Test
	public void testEventExistsAfterJoinAndQuit() {
		ps.joinEvent(1, "participant1@gmail.com","nomP1","prenomP1","Company1") ;
		assertTrue(ps.isParticipating( "participant1@gmail.com", 1));
		ps.quitEvent(1, "participant1@gmail.com");
		assertFalse(ps.isParticipating( "participant1@gmail.com", 1));
		assertTrue(es.findEventByName("Event de Adrien").size() > 0);
		assertTrue(es.findEventByName("Event de Adrien").get(0).getParticipants().size() == 0);
	}

	@Test
	public void testOrganizerExistsRemoveEvent() {
		es.removeEvent(1);
		assertTrue(es.findEventByName("Event de Adrien").size() == 0);
		assertTrue(os.exists("adrien.cadoret@gmail.com"));
	}

	@After
	public void unset() {
		databaseManager.closeEntityManagerFactory();
	}

}
