package testDAO;

import modules.DatabaseManager;
import modules.event.EventService;


public class TestDao {

	private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
	public static void main(String[] args) {
		databaseManager.populate();
		EventService es = new EventService();
		System.out.println(es.getAllEvents());
		//fonctionne !
		
	}

}
