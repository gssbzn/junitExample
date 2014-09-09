package com.example.dao.memory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.TransactionDAO;
import com.example.models.Transaction;

/**
 * Transactions DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class TransactionDAOMemoryImpl implements TransactionDAO {
    /** Singleton instance */
	private static final TransactionDAOMemoryImpl INSTANCE = new TransactionDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(TransactionDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static ConcurrentLinkedQueue<Transaction> transactions;
    /** Threadsafe ID control */
    private static AtomicLong LAST_ID;
    /** Last element */
    private Optional<Transaction> last;

    private TransactionDAOMemoryImpl(){
        transactions = new ConcurrentLinkedQueue<>();
        LAST_ID = new AtomicLong(0);
        last = Optional.empty();
    }
    
    /**
     * 
     * @return Singleton instance
     */
    public static TransactionDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    /**
     * 
     * @return new ID
     */
    private Long incrementCount() {
        return LAST_ID.incrementAndGet();
    }
	
    @Override
    public Transaction create(Transaction transaction) {
    	transaction.setId(incrementCount());
        if(transactions.add(transaction))
        	last = Optional.of(transaction);
        logger.info(transaction.toString());
        return transaction;
    }

    @Override
    public boolean update(Transaction transaction) {
    	if(transactions.remove(transaction))
    		transactions.add(transaction);
    	else
    		return false;
        return true;
    }

    @Override
    public boolean remove(Transaction transaction) {		
        return transactions.remove(transaction);
    }

    @Override
    public Optional<Transaction> find(Long id) {        
        Optional<Transaction> transaction = transactions.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
        return transaction;
    }

    @Override
    public Optional<Transaction> first() {
    	return transactions.stream().parallel().findFirst();
        
    }

    @Override
    public Optional<Transaction> last() {    	
        return last;
    }
    
    @Override
    public List<Transaction> findAll() {		
        return transactions.stream().collect(Collectors.toList());
    }

    @Override
    public Long count(){
        return transactions.stream().parallel().count();
    }

    public void empty(){
        transactions.clear();
        LAST_ID = new AtomicLong(0);
        last = Optional.empty();
    }
	
}
