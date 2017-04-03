package com.epam.coffee_van.runner;

public enum CoffeeVanTagName {
	COFFEE_VAN, PURCHASE_LIST, PURCHASE, COFFEE, NATURAL_COFFEE, INSTANT_COFFEE, BREND, TYPE_BEANS, WRAPPER, WEIGHT, PRICE, ROASTING_DEGREE, GRIDING_DEGREE, PRODUCTION, AMOUNT, BUDGET, CAPACITY;

	public static CoffeeVanTagName getElementTagName(String element) {
		switch (element) {
		case "coffeevan":
			return COFFEE_VAN;
		case "purchaseList":
			return PURCHASE_LIST;
		case "purchase":
			return PURCHASE;
		case "coffee":
			return COFFEE;
		case "naturalCoffee":
			return NATURAL_COFFEE;
		case "instantCoffee":
			return INSTANT_COFFEE;
		case "brend":
			return BREND;
		case "typeBeans":
			return TYPE_BEANS;
		case "wrapper":
			return WRAPPER;
		case "weight":
			return WEIGHT;
		case "price":
			return PRICE;
		case "roastingDegree":
			return ROASTING_DEGREE;
		case "gridingDegree":
			return GRIDING_DEGREE;
		case "production":
			return PRODUCTION;
		case "amount":
			return AMOUNT;
		case "budget":
			return BUDGET;
		case "capacity":
			return CAPACITY;
		default:
			throw new EnumConstantNotPresentException(CoffeeVanTagName.class, element);
		}
	}
}
