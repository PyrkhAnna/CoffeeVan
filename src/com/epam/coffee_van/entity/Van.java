package com.epam.coffee_van.entity;

import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Van {
	private final static Logger Log = LogManager.getLogger("Van.class");
	private TreeMap<Coffee, Integer> purchaseList;
	private int capacity;
	private int weight;

	public Van() {
		purchaseList = new TreeMap<Coffee, Integer>();
		capacity = 0;
		weight = 0;
		Log.info("New Van is created");
	}

	public Van(int capacity) {
		purchaseList = new TreeMap<Coffee, Integer>();
		this.capacity = capacity;
		weight = 0;
		Log.info("New Van is created");
	}

	public Van(TreeMap<Coffee, Integer> purchaseList, int capacity) {
		this.purchaseList = new TreeMap<Coffee, Integer>(purchaseList);
		this.capacity = capacity;
		weight = 0;
		Log.info("New Van is created");
	}

	public boolean addPurchase(Coffee coffee, Integer amount) {
		if (checkCoffee(coffee)) {
			if (ifCoffeeIsInVan(coffee, amount)) {
				return true;
			} else {
				Log.info("Failure");
				return false;
			}
		} else {
			if (ifCoffeeIsntInVan(coffee, amount)) {
				return true;
			} else {
				Log.info("Failure");
				return false;
			}
		}
	}

	public TreeMap<Coffee, Integer> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(TreeMap<Coffee, Integer> purchaseList) {
		this.purchaseList = new TreeMap<Coffee, Integer>(purchaseList);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((purchaseList == null) ? 0 : purchaseList.hashCode());
		result = prime * result + weight;
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
		Van other = (Van) obj;
		if (capacity != other.capacity)
			return false;
		if (purchaseList == null) {
			if (other.purchaseList != null)
				return false;
		} else if (!purchaseList.equals(other.purchaseList))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Van [purchaseList=" + purchaseList + ", capacity=" + capacity + ", weight=" + weight + "]";
	}

	private void loadToVan(Coffee coffee, Integer amount) {
		purchaseList.put(coffee, amount);
		this.weight = this.weight + coffee.getWeight() * amount;
		Log.info("Load Coffee to Van");
	}

	private boolean checkWeight(int weight) {
		int check = this.weight + weight;
		return check <= this.capacity;
	}

	private boolean checkAmount(Integer amount) {
		return amount != null && amount != 0 && amount > 0;
	}

	private boolean checkCoffee(Coffee coffee) {
		return coffee != null && !purchaseList.containsKey(coffee);
	}
	private boolean ifCoffeeIsInVan(Coffee coffee, Integer amount) {
		if (checkAmount(amount)) {
			if (checkWeight(coffee.getWeight() * amount)) {
				loadToVan(coffee, amount);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean ifCoffeeIsntInVan(Coffee coffee, Integer amount) {
		if (checkAmount(amount)) {
			if (checkWeight(coffee.getWeight() * amount)) {
				amount = +purchaseList.get(coffee);
				loadToVan(coffee, amount);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
