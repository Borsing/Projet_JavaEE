package testDAO;

import static org.junit.Assert.*;

import java.text.ParseException;

import javax.persistence.PersistenceException;

import exception.BeanException;
import exception.EnumException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modules.DatabaseManager;
import modules.participant.ParticipantService;

public class TestParticipantServices {
	
	private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
	private ParticipantService ps ;
	
	@Before
	public void setUp() throws PersistenceException, ClassNotFoundException, ParseException{

		databaseManager.openEntityManagerFactory();
		databaseManager.populate();
		ps = new ParticipantService();
		/*organizer1 = new OrganizerEntity("adrien.cadoret@gmail.com", "adrien", "Cadoret", "Adrien", "VIF", null);
		organizer2 = new OrganizerEntity("benjamin.robertcadoret@gmail.com", "benjamin", "Robert", "Benjamin", "AMADEUS", null);
		organizer3 = new OrganizerEntity("denis.allard@gmail.com", "denis", "Allard", "Denis", "THALES", null);

		event1 = new EventEntity("Event de Adrien", "C'est l'événement d'Adrien", (Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/11:00"),(Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/18:00"), "Adresse d'Adrien", organizer1, null);
		event2 = new EventEntity("Event de Benjamin", "C'est l'événement de Benjamin", (Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/12:00"),(Date)ArgumentsParser.convertTo(Date.class, "15/05/2016/18:00"), "Adresse de Benjamin", organizer2, null);
        event3 = new EventEntity("Event de Denis", "C'est l'événement de Denis", (Date)ArgumentsParser.convertTo(Date.class, "16/05/2016/00:00"),(Date)ArgumentsParser.convertTo(Date.class, "16/05/2016/13:00"), "Adresse de Denis", organizer3, null);

        participant1 = new ParticipantEntity("participant1@gmail.com","nomP1","prenomP1","Company1",null);
        participant2 = new ParticipantEntity("participant2@gmail.com","nomP2","prenomP2","Company2",null);
        participant3 = new ParticipantEntity("participant3@gmail.com","nomP3","prenomP3","Company3",null);
	*/
	}
	
	
	@Test
	public void testIsParticipatingJoinAndQuit() {
		assertFalse(ps.isParticipating( "participant1@gmail.com", 1));
		try {
			ps.joinEvent(1, "participant1@gmail.com","nomP1","prenomP1","Company1") ;
		} catch (BeanException e) {
			fail();
		}
		assertTrue(ps.isParticipating( "participant1@gmail.com", 1));
		ps.quitEvent(1, "participant1@gmail.com");
		assertFalse(ps.isParticipating( "participant1@gmail.com", 1));
	}
	
	@Test
	public void testCreateParticipant() {
		ps.createParticipant("participant5@gmail.com","nomP5","prenomP5","Company5");
		assertNotNull(ps.findById("participant5@gmail.com"));
	}

	@Test
	public void testJoinEventWithNewParticipant() {
		try {
			ps.joinEvent(1, "participant4@gmail.com","nomP4","prenomP4","Company4") ;
		} catch (BeanException e) {
			e.printStackTrace();
		}
		assertNotNull(ps.findById("participant4@gmail.com"));
		assertTrue(ps.isParticipating( "participant4@gmail.com", 1));
	}

	@Test
	public void testJoinEventWithExistingParticipant() {

		BeanException exception = null;

		try {
			ps.joinEvent(1, "participant3@gmail.com","nomP4","prenomP4","Company4") ;
			ps.joinEvent(1, "participant3@gmail.com","new","new","new") ;
		} catch (BeanException e) {
			exception = e;
			assertEquals(EnumException.PARTICIPANT_ALREADY_EXISTS, e.getEnumException());
		}

		if(exception == null){
			fail();
		}

	}



	@After
	public void unset() {
		databaseManager.closeEntityManagerFactory();
	}
	
}
