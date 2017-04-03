package com.epam.coffee_van.runner;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.coffee_van.entity.Coffee;
import com.epam.coffee_van.entity.ExternalData;
import com.epam.coffee_van.logic.CoffeeVanSeacher;
import com.epam.coffee_van.logic.impl.DOMCoffeeVanReader;
import com.epam.coffee_van.logic.impl.FindPurchase;
import com.epam.coffee_van.logic.impl.Uploader;
import com.epam.coffee_van.util.Configuration;

public class VanMain {
	private final static Logger Log = LogManager.getLogger();
	public static void main(String[] args)  {
		ExternalData data=null;
		Uploader uploader= null;
		TreeMap<Coffee, Integer> foundList;
		
		DOMCoffeeVanReader readData = new DOMCoffeeVanReader();
		try {
			data = readData.readCoffeeVanData(Configuration.getKey("path.read"));
		} catch (IOException e) {
			Log.debug(e);
		}
		if (data!=null) {
			uploader = new Uploader(data.getPurchaseList(),data.getCapacity(), data.getBudget());
			uploader.uploadVan();
		}
		CoffeeVanSeacher seacher = new FindPurchase();
				
		if (uploader!=null) {
			System.out.println(uploader.toString());
			foundList = seacher.findPurchase(uploader.getVan(), "price", "3.35");
			if (foundList!=null) 
				System.out.println(foundList.toString());
				else Log.info("Not found");
			foundList = seacher.findPurchase(uploader.getVan(), "weught", "20");
			if (foundList!=null) 
				System.out.println(foundList.toString());
				else Log.info("Not found");
			foundList = seacher.findPurchase(uploader.getVan(), "brend", "Kona");
			if (foundList!=null) 
				System.out.println(foundList.toString());
				else Log.info("Not found");
			
			
		}

	}

}
