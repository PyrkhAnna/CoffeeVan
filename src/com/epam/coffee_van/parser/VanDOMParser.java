package com.epam.coffee_van.parser;

import java.math.BigDecimal;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.epam.coffee_van.entity.Coffee;
import com.epam.coffee_van.entity.InstantCoffee;
import com.epam.coffee_van.entity.NaturalCoffee;
import com.epam.coffee_van.runner.ExternalData;
import com.epam.coffee_van.runner.SimpleCoffeeFactory;

public class VanDOMParser {
	private final static Logger Log = LogManager.getLogger();
	private Document document;
	private ExternalData data;
	private SimpleCoffeeFactory factory;
	private Coffee coffee;
	private TreeMap<Coffee, Integer> stockList;

	public VanDOMParser(Document document) {
		this.document = document;
		data = new ExternalData();
		factory = new SimpleCoffeeFactory();
		Log.info("VanDOMParser is created");
	}

	public ExternalData getData() {
		return data;

	}

	public void readData() {
		Element coffeeVanRoot;
		String s;

		coffeeVanRoot = document.getDocumentElement(); // root element
		s = getSingleElementContent(coffeeVanRoot, "budget");
		data.setBudget(new BigDecimal(s));
		Log.info("Budget is set");

		s = getSingleElementContent(coffeeVanRoot, "capacity");
		data.setCapacity(Integer.parseInt(s));
		Log.info("capacity is set");

		NodeList purchasesNode = coffeeVanRoot.getElementsByTagName("purchaseList");
		stockList = new TreeMap<Coffee, Integer>();
		Log.info("StockList is created"); 
		
		for (int i = 0; i < purchasesNode.getLength(); i++) {
			Element purchases = (Element) purchasesNode.item(i);

			NodeList allPurchases = purchases.getChildNodes();
			
			for (int q = 0; q < allPurchases.getLength(); q++) {
				Node purchaseNode = allPurchases.item(q);
				Element purchaseElement;
				if (purchaseNode.getNodeType() == 1) {
					purchaseElement = (Element) purchaseNode;
					NodeList allPurchasesElements = purchaseElement.getChildNodes();
					for (int j = 0; j < allPurchasesElements.getLength(); j++) {
						Node coffeeAmountNode = allPurchasesElements.item(j);
						Element coffeeAmountElement;
						if (coffeeAmountNode.getNodeType() == 1) {
							coffeeAmountElement = (Element) coffeeAmountNode;
							s = coffeeAmountElement.getTextContent();
							s = coffeeAmountElement.getNodeName();// amount or coffee
							fillStockList (coffeeAmountElement);
						}
					}
				}
			}
		}
			
		 data.setPurchaseList(stockList);
	}

	private void fillStockList(Element element) {
		String s = element.getNodeName().toUpperCase();

		if (s != null && (s.equalsIgnoreCase("NaturalCoffee") || s.equalsIgnoreCase("InstantCoffee")))
			coffee = factory.createCoffee(s);

		switch (s) {
		case "NATURALCOFFEE":
			buildCoffee(element, element.getNodeName());
			Log.info("Coffee is read");
			break;
		case "INSTANTCOFFEE":
			buildCoffee(element, element.getNodeName());
			Log.info("Coffee is read");
			break;
		case "AMOUNT":
			addPurchase(Integer.parseInt(element.getTextContent().trim()));
			Log.info("Amount is read");
			break;
		}
	}

	private void addPurchase(int amount) {
		if (stockList.get(coffee) == null)
			stockList.put(coffee, amount);
		else {
			amount = stockList.get(coffee) + amount;
			stockList.put(coffee, amount);
		}
	}

	private String getSingleElementContent(Element element, String tagName) {
		NodeList list = element.getElementsByTagName(tagName);
		Element el = (Element) list.item(0);
		String content = el.getTextContent().trim();
		return content;
	}

	private void buildCoffee(Element element, String nodeName) {
		System.out.println("nodeName " + nodeName);
		coffee = factory.createCoffee(nodeName);
		
		buildAbstractCoffee(element, nodeName);
		switch (nodeName.toUpperCase()) {
		case "NATURALCOFFEE":
			((NaturalCoffee) coffee).setRoastingDegree(getSingleElementContent(element,"roastingDegree"));
			((NaturalCoffee) coffee).setGridingDegree(getSingleElementContent(element,"gridingDegree"));
			break;
		case "INSTANTCOFFEE":
			((InstantCoffee) coffee).setProduction(getSingleElementContent(element,"production"));
			break;
		}
	}

	private void buildAbstractCoffee(Element element, String nodeName) {
		if (coffee != null) {
			coffee.setBrend(getSingleElementContent(element,"brend"));
			coffee.setTypeBeans(getSingleElementContent(element,"typeBeans"));
			coffee.setWrapper(getSingleElementContent(element,"wrapper"));
			coffee.setWeight(Integer.parseInt(getSingleElementContent(element,"weight")));
			coffee.setPrice(new BigDecimal(getSingleElementContent(element,"price")));
		}
	}

	@Override
	public String toString() {
		return "VanDOMParser [data=" + data + ", stockList=" + stockList + "]";
	}

}
