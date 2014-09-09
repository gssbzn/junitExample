package com.example.jUnitExample;

import com.example.dao.ClientDAO;
import com.example.factory.DAOFactory;
import com.example.factory.DAOFactory.DAOTYPE;
import com.example.models.Client;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DAOFactory f = DAOFactory.getDAOFactory(DAOTYPE.MEMORYFACTORY);
        ClientDAO clienteDao = f.getClientDAO();
        Client c = new Client();
        c.setName("Gus");
        clienteDao.create(c);
        Client first = clienteDao.first().get();
        System.out.println(first.toString());
    }
}
