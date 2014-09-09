package com.example.factory;

import com.example.dao.ArticleDAO;
import com.example.dao.ClientDAO;
import com.example.dao.TransactionDAO;
import com.example.dao.UserDAO;

/**
 * Fabrica de DAOs
 * 
 * @author Gustavo Bazan
 *
 */
public abstract class DAOFactory {
	/** Tipos de implementacion en DAOs */
	public enum DAOTYPE {
		MEMORYFACTORY;
	}
	
	/** Implementacion en Memoria */
	private static MemoryDAOFactory memoryDAOFactory;
	/** Implementacion con servicio Web */
	//private static RestfulDAOFactory restfulDAOFactory;
	
	/**
	 * DAO para manipulacion de Articulos
	 * @return {@code ArticleDAO}
	 */
	public abstract ArticleDAO getArticleDAO();
	
	/**
	 * DAO para manipulacion de Clientes
	 * @return {@code ClientDAO}
	 */
    public abstract ClientDAO getClientDAO();
    
    /**
     * DAO para manipulacion de Trasacciones
     * @return {@code TransactionDAO}
     */
    public abstract TransactionDAO getTransactionDAO();
    
    /**
     * DAO para manipulacion de Usuarios
     * @return {@code UserDAO}
     */
    public abstract UserDAO getUserDAO();
    
    /**
     * Obtener los daos deseados segun el tipo
     * 
     * @param daoType tipo de implementacion a usar
     * @return
     */
    public static DAOFactory getDAOFactory(DAOTYPE daoType) {
    	switch (daoType) {
		case MEMORYFACTORY:
			memoryDAOFactory = new MemoryDAOFactory();
			return memoryDAOFactory;
		default: return null;
			
		}
    }

}
