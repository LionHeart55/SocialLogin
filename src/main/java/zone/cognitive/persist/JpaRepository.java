package zone.cognitive.persist;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
//import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by shocron on 02.09.2015.
 */

public class JpaRepository<E> implements Repository<E> {

    @PersistenceContext
    private EntityManager entityManager;

    /*@Autowired(required = false)
    private SessionFactory sessionFactory;
*/
    private Class<E> entityClass;

    public Class<E> getEntityClass() {
        return entityClass;
    }

    @Required
    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void commit() {
    	entityManager.flush();
    }

    public void create(final E entity) {
        entityManager.persist(entity);
    }

    public void delete(final E entity) {
        entityManager.remove(entity);
    }

    public void delete(Serializable id) {
    	final Query query =  entityManager.createQuery("DELETE FROM "+ this.getEntityClass().getName() +" e WHERE id="+id);
    	query.executeUpdate();
    }
    
    @Override
	public void UpdateWithFilter(Serializable id, String values) {
    	final Query query =  entityManager.createQuery("UPDATE "+ this.getEntityClass().getName() +" e SET " + values + " WHERE id="+id);
    	query.executeUpdate();
	}
    
    public void refresh(final E entity) {
        entityManager.refresh(entity);
    }

    public E read(Serializable id) {
        return entityManager.find(getEntityClass(), id);
    }

    /*
    public E read(Serializable id, final boolean lock) {
        if (lock) {
            return entityManager.find(getEntityClass(), id, LockModeType.PESSIMISTIC_WRITE);
        } else {
            return read(id);
        }
    }
*/
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @SuppressWarnings("unchecked")
    public <F> List<F> loadAll(Class<F> returnType) {
    	final Query query =  entityManager.createQuery("SELECT e FROM "+ returnType.getName() +" e");
    	return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public <F> List<F> loadAllIn(Class<F> returnType, String ids) {
    	return entityManager.createQuery("SELECT e FROM "+ returnType.getName() +" e where e.id IN ("+ids+")").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public E loadById(Class<E> returnType, Serializable id) {
    	final Query query =  entityManager.createQuery("SELECT e FROM "+ returnType.getName() +" e where id="+id.toString());
    	try {
    		return (E) query.getSingleResult();
    	} catch(NoResultException nre) {
    		return null;
    	}
    }
    
    @SuppressWarnings("unchecked")
    public E loadOneByFilter(Class<E> returnType, String filter) {
    	final Query query =  entityManager.createQuery("SELECT e FROM "+ returnType.getName() +" e where "+filter);
    	try {
    		return (E) query.getSingleResult();
    	} catch(NoResultException nre) {
    		return null;
    	}
    }
    
    @SuppressWarnings("unchecked")
    public <F> List<F> loadManyByFilter(Class<F> returnType, String filter) {
    	return entityManager.createQuery("SELECT e FROM "+ returnType.getName() +" e where "+filter).getResultList();
    }
    
    public void clearEntityCache() {
        entityManager.getEntityManagerFactory().getCache().evict(this.getEntityClass());
    }

    public <T> List<T> executeNativeQuery(String query, Class<T> clazz) {
        if (clazz != null)
            return entityManager.createNativeQuery(query, clazz).getResultList();
        else
            return entityManager.createNativeQuery(query).getResultList();
    }

    public void executeNativeQuery(String query) {
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
