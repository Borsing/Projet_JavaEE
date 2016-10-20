package modules;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by adric on 20/10/2016.
 */
public interface DAOInterface<T extends AbstractEntity> {

    void create();
    void deleteById(Object id);
    void delete(T entity);
    void update(T entity);
    T findById(Object id);
    List<T> findAll();


}
