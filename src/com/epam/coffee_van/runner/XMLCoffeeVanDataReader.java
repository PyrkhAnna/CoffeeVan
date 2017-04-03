package com.epam.coffee_van.runner;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.coffee_van.parser.VanXMLHandler;
import com.epam.coffee_van.util.Configuration;

public class XMLCoffeeVanDataReader implements CoffeeVanDataReader {
	private final static Logger Log = LogManager.getLogger();

	@Override
	public ExternalData readCoffeeVanData(String path) throws IOException {
		XMLReader reader = null;
		ExternalData data = null;
		try {
			reader = XMLReaderFactory.createXMLReader();
			InputSource source = new InputSource(Configuration.getKey("path.read"));
			VanXMLHandler handler = new VanXMLHandler();
			reader.setContentHandler(handler);
			reader.parse(source);
			data = handler.getData();
		} catch (SAXException e) {
			Log.debug(e);
		}
		return data;
	}
}
