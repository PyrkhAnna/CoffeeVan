package com.epam.coffee_van.runner;

import java.io.IOException;

public interface CoffeeVanDataReader {
	public ExternalData readCoffeeVanData(String path) throws IOException;
}
