package com.epam.coffee_van.runner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.coffee_van.entity.ExternalData;
import com.epam.coffee_van.logic.impl.DOMCoffeeVanReader;
import com.epam.coffee_van.logic.impl.Uploader;
import com.epam.coffee_van.util.Configuration;

public class VanMain {
	private final static Logger Log = LogManager.getLogger();
	public static void main(String[] args)  {
		ExternalData data=null;
		Uploader uploader= null;
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
		
		//System.out.println(handler.getBudget());
		//System.out.println(handler.getCapacity());
		//System.out.println(handler.getPurchaseList().toString());
		System.out.println(uploader.toString());

	}

}
