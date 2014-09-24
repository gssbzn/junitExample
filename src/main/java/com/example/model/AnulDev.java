package com.example.model;

import com.example.enums.AnulDevMotive;


public class AnulDev extends Transaction {

	/** */
	private static final long serialVersionUID = 682385443340905339L;
	
	private Transaction originalSale;
	private AnulDevMotive motiveId;

	public AnulDev() {
		super();
	}

	public Transaction getOriginalSale() {
		return originalSale;
	}

	public void setOriginalSale(Transaction originalSale) {
		this.originalSale = originalSale;
	}

	public AnulDevMotive getMotiveId() {
		return motiveId;
	}

	public void setMotiveId(AnulDevMotive motive) {
		this.motiveId = motive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((motiveId == null) ? 0 : motiveId.hashCode());
		result = prime * result
				+ ((originalSale == null) ? 0 : originalSale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnulDev other = (AnulDev) obj;
		if (motiveId != other.motiveId)
			return false;
		if (originalSale == null) {
			if (other.originalSale != null)
				return false;
		} else if (!originalSale.equals(other.originalSale))
			return false;
		return true;
	}
	
}
