package com.epam.coffee_van.entity;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Coffee implements Comparable<Coffee> {
	private String brend;
	private String typeBeans;
	private String wrapper;
	private int weight;
	private BigDecimal price;
	private final static Logger Log = LogManager.getLogger();

	protected Coffee() {
		super();
		Log.info("New coffee is created");
	}

	protected Coffee(String brend, String typeBeans, String wrapper, int weight, BigDecimal price) {
		super();
		this.brend = brend;
		this.typeBeans = typeBeans;
		this.wrapper = wrapper;
		this.weight = weight;
		this.price = price;
		Log.info("New coffee is created");
	}

	public String getBrend() {
		return brend;
	}

	public void setBrend(String brend) {
		this.brend = brend;
	}

	public String getTypeBeans() {
		return typeBeans;
	}

	public void setTypeBeans(String typeBeans) {
		this.typeBeans = typeBeans;
	}

	public String getWrapper() {
		return wrapper;
	}

	public void setWrapper(String wrapper) {
		this.wrapper = wrapper;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int compareTo(Coffee compareCoffee) {
		int comparePrice = price.compareTo(compareCoffee.getPrice());
		int compareWeight = weight - compareCoffee.getWeight();
		return (comparePrice != 0 ? comparePrice : compareWeight);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brend == null) ? 0 : brend.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((typeBeans == null) ? 0 : typeBeans.hashCode());
		result = prime * result + weight;
		result = prime * result + ((wrapper == null) ? 0 : wrapper.hashCode());
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
		Coffee other = (Coffee) obj;
		if (brend == null) {
			if (other.brend != null)
				return false;
		} else if (!brend.equals(other.brend))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (typeBeans == null) {
			if (other.typeBeans != null)
				return false;
		} else if (!typeBeans.equals(other.typeBeans))
			return false;
		if (weight != other.weight)
			return false;
		if (wrapper == null) {
			if (other.wrapper != null)
				return false;
		} else if (!wrapper.equals(other.wrapper))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coffee [brend=" + brend + ", typeBeans=" + typeBeans + ", wrapper=" + wrapper + ", weight="
				+ weight + ", price=" + price + "]";
	}

}
