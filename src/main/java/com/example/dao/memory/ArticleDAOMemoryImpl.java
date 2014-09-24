package com.example.dao.memory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.ArticleDAO;
import com.example.model.Article;

/**
 * Article DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class ArticleDAOMemoryImpl implements ArticleDAO {
    /** Singleton instance */
	private static final ArticleDAOMemoryImpl INSTANCE = new ArticleDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(ArticleDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static ConcurrentLinkedQueue<Article> articles;
    /** Threadsafe ID control */
    private static AtomicLong LAST_ID;
    /** Last element */
    private Optional<Article> last;

    private ArticleDAOMemoryImpl(){
        articles = new ConcurrentLinkedQueue<>();
        LAST_ID = new AtomicLong(0);
        last = Optional.empty();
    }

    /**
     * 
     * @return Singleton instance
     */
    public static ArticleDAOMemoryImpl getInstance() {
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
    public Article create(Article article) {
        article.setId(incrementCount());
        if(articles.add(article))
        	last = Optional.of(article);
        logger.info(article.toString());
        return article;
    }

    @Override
    public boolean update(Article article) {
    	if(articles.remove(article))
    		articles.add(article);
    	else
    		return false;
        return true;
    }

    @Override
    public boolean remove(Article article) {		
        return articles.remove(article);
    }

    @Override
    public Optional<Article> find(Long id) {        
        Optional<Article> article = articles.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
        return article;
    }

    @Override
    public Optional<Article> first() {
    	return articles.stream().parallel().findFirst();
        
    }

    @Override
    public Optional<Article> last() {    	
        return last;
    }
    
    @Override
    public List<Article> findAll() {		
        return articles.stream().collect(Collectors.toList());
    }

    @Override
    public Long count(){
        return articles.stream().parallel().count();
    }

    public void empty(){
        articles.clear();
        LAST_ID = new AtomicLong(0);
        last = Optional.empty();
    }
	
}
