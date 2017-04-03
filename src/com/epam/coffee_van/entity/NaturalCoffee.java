package com.epam.coffee_van.entity;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NaturalCoffee extends Coffee {
	private final static Logger Log = LogManager.getLogger("NaturalCoffee.class");
	private String roastingDegree;
	private String gridingDegree;
	
	public NaturalCoffee() {
		super();
		Log.info("New NaturalCoffee is created");
	}

	public NaturalCoffee(String brend, String typeBeans, String wrapper, int weight, BigDecimal price, String roastingDegree,
			String gridingDegree) {
		super(brend, typeBeans, wrapper, weight, price);
		this.roastingDegree = roastingDegree;
		this.gridingDegree = gridingDegree;
		Log.info("New NaturalCoffee is created");
	}

	public String getRoastingDegree() {
		return roastingDegree;
	}

	public void setRoastingDegree(String roastingDegree) {
		this.roastingDegree = roastingDegree;
	}

	public String getGridingDegree() {
		return gridingDegree;
	}

	public void setGridingDegree(String gridingDegree) {
		this.gridingDegree = gridingDegree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((gridingDegree == null) ? 0 : gridingDegree.hashCode());
		result = prime * result + ((roastingDegree == null) ? 0 : roastingDegree.hashCode());
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
		NaturalCoffee other = (NaturalCoffee) obj;
		if (gridingDegree == null) {
			if (other.gridingDegree != null)
				return false;
		} else if (!gridingDegree.equals(other.gridingDegree))
			return false;
		if (roastingDegree == null) {
			if (other.roastingDegree != null)
				return false;
		} else if (!roastingDegree.equals(other.roastingDegree))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Natural "+super.toString()+" [roastingDegree=" + roastingDegree + ", gridingDegree=" + gridingDegree + "]";
	}

}
