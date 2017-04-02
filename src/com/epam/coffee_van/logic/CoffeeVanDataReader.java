package com.epam.coffee_van.logic;

import java.io.IOException;
import com.epam.coffee_van.entity.ExternalData;

public interface CoffeeVanDataReader {
	public ExternalData readCoffeeVanData(String path) throws IOException;
}
