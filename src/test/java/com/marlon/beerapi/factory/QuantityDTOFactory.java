package com.marlon.beerapi.factory;

import com.marlon.beerapi.dto.QuantityDTO;

public class QuantityDTOFactory {

	public static QuantityDTO createQuantityDTO() {
		return new QuantityDTO();
	}
	
	public static QuantityDTO createQuantityDTO(int quantity) {
		return new QuantityDTO(quantity);
	}
}
