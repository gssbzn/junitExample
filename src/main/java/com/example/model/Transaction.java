package com.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.example.enums.TransactionState;
import com.example.enums.TransactionType;

@XmlRootElement
@XmlType (propOrder={"id","store","pos","number","active","client","user"})
public class Transaction implements Model<Long> {

	/** */
	private static final long serialVersionUID = -159896284282837636L;
	
	private Long id;
	private String store;
	private String pos;
	private String number;
	private boolean active;
	private Client client;
	private User user;
	
	private Date date = new Date();
	private TransactionType transactionType;
	private TransactionState transactionState;
	
	private Collection<Article> articles;
	private BigDecimal articlesCount = BigDecimal.ZERO;
	
	private Collection<Payment> payments;
	
	private BigDecimal totalTaxAmount = BigDecimal.ZERO;
	private BigDecimal totalCost = BigDecimal.ZERO;
	private BigDecimal totalPaid = BigDecimal.ZERO;
	private BigDecimal diference = BigDecimal.ZERO;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
		this.active = true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType tt) {
		transactionType = tt;
	}

	
	public TransactionState getTransactionState() {
		return transactionState;
	}

	public void setTransactionState(TransactionState transactionState) {
		this.transactionState = transactionState;
	}

	public void addArticle(Article art) {
		if(this.articles==null){
			this.articles = new ArrayList<Article>();
			this.articlesCount=BigDecimal.ZERO;
		}
		this.articles.add(art);
		this.totalCost = this.totalCost.add(art.getTotalCost());
		this.totalTaxAmount = this.totalTaxAmount.add(art.getItemTaxAmount().multiply(art.getItems()));
		this.active = true;
		this.articlesCount = this.articlesCount.add(art.getItems());
	}

	public void removeArticle(Article art) {
		this.articles.remove(art);
		this.totalCost = this.totalCost.subtract(art.getTotalCost());
		this.totalTaxAmount = this.totalTaxAmount.subtract(art.getItemTaxAmount().multiply(art.getItems()));
		this.articlesCount = this.articlesCount.subtract(art.getItems());
		if(this.articles.size()==0){
			this.articles=null;
		}
	}
	
	public void setArticles(Collection<Article> articles) {
		this.articles = null;
		for(Article art: articles){
			addArticle(art);
		}
	}
	
	public Collection<Article> getArticles() {
		return articles;
	}

	public void addPayment(Payment pay) {
		if(this.payments==null){
			this.payments = new ArrayList<Payment>();
		}
		this.payments.add(pay);
		this.totalPaid = this.totalPaid.add(pay.getAmmount());
		updateDifference();
	}
	
	public void removePayment(Payment pay) {
		this.payments.remove(pay);
		this.totalPaid = this.totalPaid.subtract(pay.getAmmount());
		updateDifference();
		if(this.payments.size()==0){
			this.payments=null;
		}
	}

	public Collection<Payment> getPayments() {
		return this.payments;
	}
	
	public void setPayments(Collection<Payment> payments) {
		this.payments = null;
		for(Payment pay:payments){
			addPayment(pay);
		}
	}
	
	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public BigDecimal getTotalPay() {
		return totalPaid;
	}

	public BigDecimal getDiference() {
		return diference;
	}

	private void updateDifference(){
		this.diference = this.totalCost.subtract(this.totalPaid);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result
				+ ((articles == null) ? 0 : articles.hashCode());
		result = prime * result
				+ ((articlesCount == null) ? 0 : articlesCount.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((diference == null) ? 0 : diference.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result
				+ ((payments == null) ? 0 : payments.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		result = prime * result
				+ ((totalCost == null) ? 0 : totalCost.hashCode());
		result = prime * result
				+ ((totalPaid == null) ? 0 : totalPaid.hashCode());
		result = prime * result
				+ ((totalTaxAmount == null) ? 0 : totalTaxAmount.hashCode());
		result = prime
				* result
				+ ((transactionState == null) ? 0 : transactionState.hashCode());
		result = prime * result
				+ ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
}
