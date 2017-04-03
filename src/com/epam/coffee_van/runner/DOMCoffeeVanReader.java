package com.epam.coffee_van.runner;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.epam.coffee_van.parser.VanDOMParser;
import com.epam.coffee_van.util.Configuration;

public class DOMCoffeeVanReader implements CoffeeVanDataReader {
	private final static Logger Log = LogManager.getLogger();
	@Override
	public ExternalData readCoffeeVanData(String path) throws IOException {
		
		Log.info("Start to read data");
		VanDOMParser parser= null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;		
		Document document;
		try{
			builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(Configuration.getKey("path.read")));
			parser = new VanDOMParser(document);
			parser.readData();
			Log.info("Finish to read document");
		} catch (SAXException|ParserConfigurationException e){
			Log.debug(e);
		}
		Log.info("Finish to read data");
		return parser.getData();
	}
}
