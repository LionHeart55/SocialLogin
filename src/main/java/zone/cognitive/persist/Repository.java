package zone.cognitive.persist;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for repository. Repository is bean which wraps low-level calls to
 * persistence and responsible for entity of one type
 */
public interface Repository<E> {

    /**
     * Reads entity by its id.
     *
     * @param id
     * @return
     */
    E read(Serializable id);

    /**
     * Reads entity by its id and locks it for update.
     *
     * @param id
     * @return
     */
    //E read(Serializable id, boolean lock);

    public void commit();
    
    /**
     * Saves new entity.
     */
    void create(E entity);
    
    /**
     * Updates entity.
     *
     * @param entity
     */
    void update(E entity);

    /**
     * Deletes record related to entity.
     *
     * @param entity
     */
    void delete(E entity);

    /**
     * Deletes records by Id.
     *
     * @param id
     */
    void delete(Serializable id);
    
    /**
     * Refreshes given entity.
     *
     * @param entity
     */
    void refresh(E entity);

    <F> List<F> loadAll(Class<F> returnType);
    
    <F> List<F> loadAllIn(Class<F> returnType, String ids);
    
    E loadById(Class<E> returnType, Serializable id);
    
    E loadOneByFilter(Class<E> returnType, String filter);
    
    <F> List<F> loadManyByFilter(Class<F> returnType, String filter);
    
    void UpdateWithFilter(Serializable id, String values);

    /**
     * Clears cache for entity class.
     */
    void clearEntityCache();

    /**
     * Execute native select query.
     */
    <T> List<T> executeNativeQuery(String query, Class<T> clazz);

    /**
     * Execute query without result.
     *
     * @param query
     */
    void executeNativeQuery(String query);
}
