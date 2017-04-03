package com.epam.coffee_van.parser;

import java.math.BigDecimal;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.epam.coffee_van.entity.Coffee;
import com.epam.coffee_van.entity.InstantCoffee;
import com.epam.coffee_van.entity.NaturalCoffee;
import com.epam.coffee_van.runner.ExternalData;
import com.epam.coffee_van.runner.SimpleCoffeeFactory;

public class VanXMLHandler extends DefaultHandler {
	private final static Logger Log = LogManager.getLogger("VanXMLHandler.class");
	private SimpleCoffeeFactory factory;
	private Coffee coffee;
	private String value;
	private ExternalData data;
	private TreeMap<Coffee, Integer> purchaseList;
	private int amount;

	public VanXMLHandler() {
		this.factory = new SimpleCoffeeFactory();
	}

	private void addPurchase() {
		if (purchaseList.get(coffee) == null) {
			purchaseList.put(coffee, amount);
		} else {
			amount = purchaseList.get(coffee) + amount;
			purchaseList.put(coffee, amount);
		}
	}

	public ExternalData getData() {
		return data;
	}

	@Override
	public void startDocument() throws SAXException {
		Log.info("startDocument");
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		Log.info("endDocument");
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		Log.info("start " + qName);
		System.out.println(qName);
		if (qName.equals("coffeevan"))
			data = new ExternalData();
		if (qName.equals("purchaseList"))
			purchaseList = new TreeMap<Coffee, Integer>();
		value = qName;
		if (value != null&&(value.equals("NaturalCoffee")||value.equals("InstantCoffee")))
			coffee = factory.createCoffee(value);
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		Log.info("end " + qName);
		System.out.println("end  " + qName);
		if (qName.equals("purchase"))
			addPurchase();
		if (qName.equals("purchaseList"))
			data.setPurchaseList(purchaseList);
		value = "";
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		Log.info("characters");
		switch (value) {
		case "brend":
			Log.info("characters brend");
			coffee.setBrend(new String(ch, start, length));
			break;
		case "typeBeans":
			coffee.setTypeBeans(new String(ch, start, length));
			break;
		case "wrapper":
			coffee.setWrapper(new String(ch, start, length));
			break;
		case "weight":
			coffee.setWeight(Integer.parseInt(new String(ch, start, length)));
			break;
		case "price":
			coffee.setPrice(new BigDecimal(new String(ch, start, length)));
			break;
		case "roastingDegree":
			((NaturalCoffee) coffee).setRoastingDegree(new String(ch, start, length));
			break;
		case "gridingDegree":
			((NaturalCoffee) coffee).setGridingDegree(new String(ch, start, length));
			break;
		case "production":
			((InstantCoffee) coffee).setProduction(new String(new String(ch, start, length)));
			break;
		case "amount":
			this.amount = Integer.parseInt(new String(ch, start, length));
			break;
		case "budget":
			data.setBudget(new BigDecimal(new String(ch, start, length)));
			break;
		case "capacity":
			data.setCapacity(Integer.parseInt(new String(ch, start, length)));
			break;
		}
	}

}
