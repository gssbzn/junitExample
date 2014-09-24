package com.example.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.example.enums.PaymentMethod;

@XmlRootElement
@XmlType (propOrder={"id","payMethod","ammount"})
public class Payment implements Model<String> {

	/** */
	private static final long serialVersionUID = 2623328335018916229L;
	
	private String id;
	private PaymentMethod payMethod;
	private BigDecimal ammount;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}

	public BigDecimal getAmmount() {
		return ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ammount == null) ? 0 : ammount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((payMethod == null) ? 0 : payMethod.hashCode());
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
		Payment other = (Payment) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

}
