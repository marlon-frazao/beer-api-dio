package com.marlon.beerapi.services.exceptions;

public class BeerStockNegativeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BeerStockNegativeException(Long id, int quantityToDecrement) {
        super(String.format("There are no beers with ID %s enough to decrement %s", id, quantityToDecrement));
    }
}
