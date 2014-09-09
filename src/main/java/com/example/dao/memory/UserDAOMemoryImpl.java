package com.example.dao.memory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.UserDAO;
import com.example.models.User;

/**
 * User DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class UserDAOMemoryImpl implements UserDAO {
    /** Singleton instance */
	private static final UserDAOMemoryImpl INSTANCE = new UserDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(UserDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static ConcurrentLinkedQueue<User> users;
    /** Threadsafe ID control */
    private static AtomicLong LAST_ID;
    /** Last element */
    private Optional<User> last;

    private UserDAOMemoryImpl(){
        users = new ConcurrentLinkedQueue<>();
        LAST_ID = new AtomicLong(0);
        last = Optional.empty();
    }
    
    /**
     * 
     * @return Singleton instance
     */
    public static UserDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    /**
     * 
     * @return new ID
     */
    private Long incrementCount() {
        return LAST_ID.incrementAndGet();
    }
	
    /**
     * Create a new User
     * 
     * @return the new user
     */
    @Override
    public User create(User user) {
        user.setId(incrementCount());
        if(users.add(user))
        	last = Optional.of(user);
        logger.info(user.toString());
        return user;
    }

    @Override
    public boolean update(User user) {
    	if(users.remove(user))
    		users.add(user);
    	else
    		return false;
        return true;
    }

    @Override
    public boolean remove(User user) {		
        return users.remove(user);
    }

    @Override
    public Optional<User> find(Long id) {        
        Optional<User> user = users.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
        return user;
    }

    @Override
    public Optional<User> first() {
    	return users.stream().parallel().findFirst();
        
    }

    @Override
    public Optional<User> last() {    	
        return last;
    }
    
    @Override
    public List<User> findAll() {		
        return users.stream().collect(Collectors.toList());
    }

    @Override
    public Long count(){
        return users.stream().parallel().count();
    }

    public void empty(){
        users.clear();
        LAST_ID = new AtomicLong(0);
        last = Optional.empty();
    }
	
}
