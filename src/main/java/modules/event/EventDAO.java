package modules.event;

import modules.DAOInterface;


/**
 * Created by adric on 10/10/2016.
 */
public class EventDAO implements DAOInterface<EventEntity>{

	@Override
	public Class<EventEntity> getEntityClass() {
		return EventEntity.class;
	}



}
