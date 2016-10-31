package modules.participant;

import exception.BeanException;
import exception.EnumException;
import modules.Field;
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
		
		Field<ParticipantEntity> fieldPar = new Field<ParticipantEntity>() ;
		fieldPar.setFieldTarget("participants")
				.setOperator(Field.Operator.IM)
				.setValueTarget(par);
		
		Field<Integer> fieldID = new Field<Integer>() ;
		fieldID.setFieldTarget("id")
				.setOperator(Field.Operator.EQ)
				.setValueTarget(event_id);
			
		return !eventDao.findByCriteria(Field.BooleanOperator.AND, fieldPar,fieldID).isEmpty() ;
	}
	

	public ParticipantEntity createParticipant(String participant_mail, String last_name, String first_name, String company){
		ParticipantEntity par = new ParticipantEntity(participant_mail, last_name, first_name, company, null);
		this.parDao.create(par);
		return par ;
	}
	
	public boolean joinEvent(int event_id, String participant_mail, String last_name, String first_name, String company) throws BeanException {
		ParticipantEntity par = parDao.findById(participant_mail);
		EventEntity event = eventDao.findById(event_id);

		if(par == null)
			par = createParticipant(participant_mail, last_name, first_name,company);
		
		if(isParticipating(participant_mail, event_id)) {
			throw new BeanException(EnumException.PARTICIPANT_ALREADY_EXISTS);
		}
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
