package com.epam.coffee_van.entity;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstantCoffee extends Coffee {
	private String production;
	private final static Logger Log = LogManager.getLogger();

	public InstantCoffee() {
		super();
		Log.info("New InstantCoffee is created");
	}

	public InstantCoffee(String brend, String typeBeans, String wrapper, int weight, BigDecimal price, String production) {
		super(brend, typeBeans, wrapper, weight, price);
		this.production = production;
		Log.info("New InstantCoffee is created");
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((production == null) ? 0 : production.hashCode());
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
		InstantCoffee other = (InstantCoffee) obj;
		if (production == null) {
			if (other.production != null)
				return false;
		} else if (!production.equals(other.production))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instant "+super.toString()+" [production=" + production + "]";
	}

}
