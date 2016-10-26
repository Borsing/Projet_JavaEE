package modules;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface DAOInterface<T extends AbstractEntity> {

    default void create(T entity) {
    	EntityManager em = DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	em.getTransaction().begin();
    	em.persist(entity);
    	em.getTransaction().commit();
    	em.close();
    }
    
    default void deleteById(Object id){
    	EntityManager em =  DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	em.getTransaction().begin();
    	T entity = em.find(this.getEntityClass(), id);
    	
    	em.remove(entity);
    	
    	em.getTransaction().commit();
    	em.close();
    }
    
    default void delete(T entity){
    	EntityManager em =  DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	em.getTransaction().begin();
    	
    	em.remove(entity);
    	
    	em.getTransaction().commit();
    	em.close();
    }
    
    default boolean update(T entity){
    	EntityManager em = DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	em.getTransaction().begin();
    	
    	T entityFound = em.find(this.getEntityClass(), entity.getId());
    	
    	if(entityFound == null)
    		return false; 
    	
    	entityFound = entity ;
    	
    	em.persist(entityFound);
    	
    	em.getTransaction().commit();
    	em.close();
    	return true ;
    }
    
    
    default T findById(Object id) {
    	EntityManager em = DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	T entity = em.find(this.getEntityClass(), id);
    	em.close();
    	return entity ;
    }
    
    default List<T> findAll(){
    	return findByPredicate() ; //no predicate so all values
    }
    
    default List<T> findByPredicate(Predicate... predicates){
    	EntityManager em = DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<T> criteria = cb.createQuery(this.getEntityClass());
    	
    	//Criteria Definition
    	Root<T> entity = criteria.from(this.getEntityClass());
    	criteria.select(entity).where(predicates) ; //no condition
    	
    	Query query = em.createQuery(criteria);
    	
    	// ? to check
    	List<T> all =  query.getResultList() ;
    	return all ;
    }
    

    Class<T> getEntityClass();


}
