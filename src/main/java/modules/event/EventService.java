package modules.event;

import java.util.List;

public class EventService {
	
	EventDAO eventDao = new EventDAO();
	
	//To improve OF COURSE
	public List<EventEntity> getAllEvents(){return eventDao.findAll();}

	public EventEntity getEventById(String id){ return eventDao.findById(Integer.parseInt(id.toString()));}

}
