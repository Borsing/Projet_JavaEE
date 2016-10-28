package modules.participant;

import modules.event.EventDAO;
import modules.event.EventEntity;

public class ParticipantService {
	
	
	ParticipantDAO parDao = new ParticipantDAO();
	EventDAO eventDao = new EventDAO();

	public ParticipantEntity findById(String id){
		return parDao.findById(id);
	}
	
	public boolean isParticipating(String participant_mail, int event_id){
		ParticipantEntity par = parDao.findById(participant_mail);
		EventEntity event =		eventDao.findById(event_id);
		
		return par.getEvents().contains(event);
	}
	

	public ParticipantEntity createParticipant(String participant_mail, String last_name, String first_name, String company){
		ParticipantEntity par = new ParticipantEntity(participant_mail, last_name, first_name, company, null);
		this.parDao.create(par);
		return par ;
	}
	
	public boolean joinEvent(int event_id, String participant_mail, String last_name, String first_name, String company){
		ParticipantEntity par = parDao.findById(participant_mail);
		EventEntity event = eventDao.findById(event_id);

		if(par == null)
			par = createParticipant(participant_mail, last_name, first_name,company);
		
		if(isParticipating(participant_mail, event_id))
			return false; 
				
		par.setLast_name(last_name);
		par.setFirst_name(first_name);
		par.setCompany(company);
		
		parDao.update(par);
		
    	event.getParticipants().add(par);

		return eventDao.update(event);
	}
	
	public boolean quitEvent(int eventId, String participantId)	{
		ParticipantEntity participant = parDao.findById(participantId);
		EventEntity event = eventDao.findById(eventId);
		
		event.getParticipants().remove(participant);
		
		return eventDao.update(event);
	}
}
