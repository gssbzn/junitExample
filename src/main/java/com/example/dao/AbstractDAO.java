package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.models.Model;

/**
 * Clase abstracta para generalizar los metodos en un DAO.
 * <p> Permite tener operaciones CRUD para el modelo deseado.
 * <p> El objeto a usar por el DAO debe implementar la interface {@code Model}.
 * 
 * @author Gustavo Bazan
 *
 * @param <T> Dao Model Type
 * @param <K> Model Key Type
 */
public interface AbstractDAO<T extends Model<K>, K> {
	
    /**
     * Store a new Object of Type T
     * @param object
     * 			Object to create
     * @return
     * 			The Created Object
     */
    public T create(T object);

    /**
     * Update the given Object
     * @param object
     * 			Object to Update
     * @return
     * 			{@code true} if successful update
     */
    public boolean update(T object);

    /**
     * Delete the given Object
     * @param object
     * 			Object to delete
     * @return
     * 			{@code true} if successful delete
     */
    public boolean remove(T object);

    /**
     * Find the Object with the given id
     * @param id
     * 			The id of the object
     * @return
     */
    public Optional<T> find(K id);
    
    /**
     * 
     * @return first element
     */
    public Optional<T> first();
    
    /**
     * 
     * @return last element
     */
    public Optional<T> last();

    /**
     * Get all
     * @return Object List
     */
    public List<T> findAll();
    
    /**
     * 
     * @return No of records
     */
    public Long count();

}
