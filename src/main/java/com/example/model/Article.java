package com.example.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.example.enums.SaleUnit;

@XmlRootElement
@XmlType (propOrder={"id","code","name","items","itemCost","ItemTaxAmount"})
public final class Article implements Model<Long> {

	/**	 */
	private static final long serialVersionUID = 5845081352321961718L;

	private Long id;

	private String code;
	private String name;
	private BigDecimal items = BigDecimal.ZERO;
	private BigDecimal itemCost = BigDecimal.ZERO;
	private BigDecimal itemTaxAmount;
	/**
	 * Precio total de los articulos
	 */
	private BigDecimal totalCost = BigDecimal.ZERO;
	private SaleUnit saleUnit;

	
	public Article() {

	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getItems() {
		return items;
	}

	public void setItems(BigDecimal items) {
		this.items = items;
	}

	public BigDecimal getItemCost() {
		return itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public SaleUnit getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(SaleUnit saleUnit) {
		this.saleUnit = saleUnit;
	}

	public BigDecimal getItemTaxAmount() {
		return itemTaxAmount;
	}

	public void setItemTaxAmount(BigDecimal itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((itemCost == null) ? 0 : itemCost.hashCode());
		result = prime * result
				+ ((itemTaxAmount == null) ? 0 : itemTaxAmount.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((saleUnit == null) ? 0 : saleUnit.hashCode());
		result = prime * result
				+ ((totalCost == null) ? 0 : totalCost.hashCode());
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
		Article other = (Article) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
}
