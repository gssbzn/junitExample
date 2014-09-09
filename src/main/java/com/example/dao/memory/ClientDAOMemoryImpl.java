package com.example.dao.memory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.ClientDAO;
import com.example.models.Client;

/**
 * Client DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class ClientDAOMemoryImpl implements ClientDAO {
    /** Singleton instance */
	private static final ClientDAOMemoryImpl INSTANCE = new ClientDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(ClientDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static ConcurrentLinkedQueue<Client> clients;
    /** Threadsafe ID control */
    private static AtomicInteger LAST_ID;
    /** Last element */
    private Optional<Client> last;

    private ClientDAOMemoryImpl(){
        clients = new ConcurrentLinkedQueue<>();
        LAST_ID = new AtomicInteger(0);
        last = Optional.empty();
    }

    /**
     * 
     * @return Singleton instance
     */
    public static ClientDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    /**
     * 
     * @return new ID
     */
    private Integer incrementCount() {
        return LAST_ID.incrementAndGet();
    }
	
    /**
     * Create a new Client
     * 
     * @return the new client
     */
    @Override
    public Client create(Client client) {
        client.setId(incrementCount().toString());
        if(clients.add(client))
        	last = Optional.of(client);
        logger.info(client.toString());
        return client;
    }

    @Override
    public boolean update(Client client) {
    	if(clients.remove(client))
    		clients.add(client);
    	else
    		return false;
        return true;
    }

    @Override
    public boolean remove(Client client) {		
        return clients.remove(client);
    }

    @Override
    public Optional<Client> find(String id) {        
        Optional<Client> client = clients.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
        return client;
    }

    @Override
    public Optional<Client> first() {
    	return clients.stream().parallel().findFirst();
        
    }

    @Override
    public Optional<Client> last() {    	
        return last;
    }
    
    @Override
    public List<Client> findAll() {		
        return clients.stream().collect(Collectors.toList());
    }

    @Override
    public Long count(){
        return clients.stream().parallel().count();
    }

    public void empty(){
        clients.clear();
        LAST_ID = new AtomicInteger(0);
        last = Optional.empty();
    }
	
}
