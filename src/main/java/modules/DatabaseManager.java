package modules;

import modules.event.EventEntity;
import modules.organizer.OrganizerEntity;
import modules.participant.ParticipantEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by adric on 20/10/2016.
 */
public class DatabaseManager {

    private static DatabaseManager INSTANCE = new DatabaseManager();
    private static final String PERSISTENCE_UNIT = "eventmanager";
    private static EntityManagerFactory entityManagerFactory;

    /** Constructeur privé */
    private DatabaseManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static DatabaseManager getInstance()
    {	return INSTANCE;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void populate() throws PersistenceException {

        try {
            EntityManager entityManager = getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();

            Collection<AbstractEntity> entities = new ArrayList<>();

            OrganizerEntity organizer1 = new OrganizerEntity("adrien.cadoret@gmail.com", "adrien", "Cadoret", "Adrien", "VIF", null);
            OrganizerEntity organizer2 = new OrganizerEntity("benjamin.robertcadoret@gmail.com", "benjamin", "Robert", "Benjamin", "AMADEUS", null);
            OrganizerEntity organizer3 = new OrganizerEntity("denis.allard@gmail.com", "denis", "Allard", "Denis", "THALES", null);

            EventEntity event1 = new EventEntity("Event de Adrien", "C'est l'événement d'Adrien", new Date(),new Date(), "Adresse d'Adrien", organizer1, null);
            EventEntity event2 = new EventEntity("Event de Benjamin", "C'est l'événement de Benjamin", new Date(),new Date(), "Adresse de Benjamin", organizer2, null);
            EventEntity event3 = new EventEntity("Event de Denis", "C'est l'événement de Denis", new Date(),new Date(), "Adresse de Denis", organizer3, null);

            ParticipantEntity participant1 = new ParticipantEntity("participant1@gmail.com","nomP1","prenomP1","Company1",null);
            ParticipantEntity participant2 = new ParticipantEntity("participant2@gmail.com","nomP2","prenomP2","Company2",null);
            ParticipantEntity participant3 = new ParticipantEntity("participant3@gmail.com","nomP3","prenomP3","Company3",null);


            entities.add(organizer1);
            entities.add(organizer2);
            entities.add(organizer3);
            entities.add(event1);
            entities.add(event2);
            entities.add(event3);
            entities.add(participant1);
            entities.add(participant2);
            entities.add(participant3);

            for (AbstractEntity entity : entities) {
                entityManager.persist(entity);
            }

            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (PersistenceException e){
            throw e;
        }


    }



    public void closeEntityManagerFactory() {
        if(getEntityManagerFactory().isOpen()){
            getEntityManagerFactory().close();
        }
    }

    public Collection<Object> listEntitiesOfDatabase() {
        Collection<Object> result = new ArrayList<>();
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        result.add(entityManager.createQuery("Select t from EventEntity t").getResultList());
        result.add(entityManager.createQuery("Select t from OrganizerEntity t").getResultList());
        result.add(entityManager.createQuery("Select t from ParticipantEntity t").getResultList());

        return result;
    }

}
