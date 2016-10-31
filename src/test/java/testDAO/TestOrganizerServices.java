package testDAO;

import exception.BeanException;
import exception.EnumException;
import modules.ArgumentsParser;
import modules.DatabaseManager;
import modules.event.EventEntity;
import modules.event.EventService;
import modules.organizer.OrganizerEntity;
import modules.organizer.OrganizerService;
import modules.participant.ParticipantService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TestOrganizerServices {

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
		databaseManager.openEntityManagerFactory();
		databaseManager.populate();
		
		organizer1 = new OrganizerEntity("adrien.cadoret@gmail.com", "adrien", "Cadoret", "Adrien", "VIF", null);
		
		event1 = new EventEntity("Event de Adrien", "C'est l'événement d'Adrien", (Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/11:00"),(Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/18:00"), "Adresse d'Adrien", organizer1, new LinkedList<>());
		event1.setId(1);
	}

	@Test
	public void testOrganizerExistsAfterRemovingEvent() {
		es.removeEvent(1);
		assertTrue(es.findEventByName(event1.getName()).size() == 0);
		assertTrue(os.exists("adrien.cadoret@gmail.com"));
	}

	@Test
	public void testCheckLogin() {

		assertTrue(os.checkLogin((String) organizer1.getId(), organizer1.getPassword()));
	}

	@Test
	public void testUpdateProfil() {
		os.updateProfil(organizer1.getMail(), organizer1.getMail(), "newpassword", organizer1.getLast_name(), organizer1.getFirst_name(), organizer1.getCompany());
		organizer1.setPassword("newpassword");
		assertEquals(organizer1, os.findOrganizerById((String) organizer1.getId()));
		}

	@Test
	public void testFindOrganizerById(){
		assertEquals(organizer1, os.findOrganizerById((String) organizer1.getId()));
	}

	@Test
	public void testRegisterWithNotExistingMail(){
		try {
			os.register("mail", "password", "lastname", "firstname","company");
		} catch (BeanException e) {
			fail();
		}
		assertTrue(os.exists("mail"));
	}

	@Test
	public void testRegisterWithExistingMail(){
		try {
			os.register("adrien.cadoret@gmail.com", "password", "lastname", "firstname","company");
		} catch (BeanException e) {
			assertEquals(EnumException.USER_ALREADY_EXISTS, e.getEnumException());
		}
	}

	@Test
	public void testUnregister(){
		try {
			os.unregister("adrien.cadoret@gmail.com");
		} catch (BeanException e) {
			fail();
		}
		assertFalse(os.exists("adrien.cadoret@gmail.com"));
	}

	@Test
	public void testChangePassword(){
		os.changePassword("adrien.cadoret@gmail.com","newpassword");

		assertEquals(os.findOrganizerById("adrien.cadoret@gmail.com").getPassword(), "newpassword");
	}

	@After
	public void unset() {
		databaseManager.closeEntityManagerFactory();
	}

}
