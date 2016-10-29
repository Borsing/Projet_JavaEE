package modules;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import modules.Field.BooleanOperator;

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

		/*T entity = em.merge(obj);
		entityManager.remove(obj);

		em.remove(entity);
    	
    	em.getTransaction().commit();
    	em.close();*/
		T obj = em.merge(entity);
		em.remove(obj);
		em.getTransaction().commit();
		em.close();
	}
    
    default boolean update(T entity){
    	EntityManager em = DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	em.getTransaction().begin();
    	    	
    	T entityFound = em.merge(entity);
    	
    	if(entityFound == null)
    		return false; 
    	

    	entityFound = entity ;
    	
    	em.getTransaction().commit();
    	em.close();
    	return true ;
    }
    
    
    default T findById(Object id) {
    	EntityManager em = DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	em.getTransaction().begin();
    	T entity = em.find(this.getEntityClass(), id);
    	em.close();
    	return entity ;
    }
    
    default List<T> findAll(){
    	return findByCriteria(BooleanOperator.OR) ; //no predicate so all values
    }
    
    //findByCriteria(new Field<String>(), new Field<Integer>())
    
    default <K extends Comparable<K>> List<T> findByCriteria(Field<?> field){ 
    	return findByCriteria(Field.BooleanOperator.AND,field);
    }
    
    default <K extends Comparable<K>> List<T> findByCriteria(Field.BooleanOperator booleanOperator,Field<?>... fields){
    	EntityManager em = DatabaseManager.getEntityManagerFactory().createEntityManager() ;
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<T> criteria = cb.createQuery(this.getEntityClass());
    	    	
    	//Criteria Definition
    	Root<T> root = criteria.from(this.getEntityClass());
    
    	Predicate where = cb.conjunction();
    	for (Field<?> field : fields)
    	{
    			if(field.getOperator().equals(Field.Operator.GE)) {
    				FieldComparable<K> comparableField = (FieldComparable<K>)field ;
    				if(booleanOperator.equals(Field.BooleanOperator.AND))
    					where = cb.and(where, cb.greaterThanOrEqualTo(root.get(comparableField.getFieldTarget()), comparableField.getValue()));
    				
    				else
    					where = cb.or(where, cb.greaterThanOrEqualTo(root.get(comparableField.getFieldTarget()), comparableField.getValue()));
    			}
    			
    			else if(field.getOperator().equals(Field.Operator.LE)) {
    				FieldComparable<K> comparableField = (FieldComparable<K>)field ;
    				if(booleanOperator.equals(Field.BooleanOperator.AND))
    					where = cb.and(where, cb.lessThanOrEqualTo(root.get(comparableField.getFieldTarget()), comparableField.getValue()));
    				
    				else
    					where = cb.or(where, cb.lessThanOrEqualTo(root.get(comparableField.getFieldTarget()), comparableField.getValue()));
    			}

    			else if(field.getOperator().equals(Field.Operator.EQ)){
        			if(booleanOperator.equals(Field.BooleanOperator.AND))
        				where = cb.and(where, cb.equal(root.get(field.getFieldTarget()), field.getValue()));
        			else
        				where = cb.or(where, cb.equal(root.get(field.getFieldTarget()), field.getValue()));	
        		} 
    			
    			else if(field.getOperator().equals(Field.Operator.IM)){
        			if(booleanOperator.equals(Field.BooleanOperator.AND))
        				where = cb.and(cb.isMember(field.getValue(), root.get(field.getFieldTarget())));
        			
        			else
        				where = cb.or(cb.isMember(field.getValue(), root.get(field.getFieldTarget())));
        		}
 
    	}
    	
    	criteria.select(root).where(where) ;

    	
    	Query query = em.createQuery(criteria);
    	
    	// ? to check
    	List<T> all =  query.getResultList() ;
    	return all ;
    }
    

    Class<T> getEntityClass();


}
