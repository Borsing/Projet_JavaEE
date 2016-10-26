package modules.event;

import java.util.List;

public class EventService {
	
	EventDAO eventDao = new EventDAO();
	
	//To improve OF COURSE
	public List<EventEntity> getAllEvents(){
		return eventDao.findAll();
	}
}
