package by.epam.ft.dao;

import by.epam.ft.entity.MyEntity;

import java.util.List;

/**
 * Dao interface
 * @param <T>
 */
public interface DAO<T> {
    /**
     * Show entity by id
     * @param id
     * @return Entity
     */
    MyEntity showById(int id);

    /**
     * Show all Entities
     * @return all entities of table
     */
    List<T> showAll();

    /**
     * Update info in table
     * @param t
     * @param query
     * @return true - if successful, otherwise - false
     */
    boolean updateInfo(T t, String query);
    /**
     * delete info from table
     * @param t
     * @param query
     * @return true - if successful, otherwise - false
     */
    boolean deleteInfo(T t, String query);
    /**
     * insert info in table
     * @param t
     * @param query
     * @return true - if successful, otherwise - false
     */
    boolean insertInfo(T t, String query);


}
