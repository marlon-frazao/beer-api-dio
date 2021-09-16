package com.marlon.beerapi.services.exceptions;

public class BeerStockExceededException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BeerStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Beers with %s ID to increment informed exceeds the max stock capacity: %s", id, quantityToIncrement));
    }
}
