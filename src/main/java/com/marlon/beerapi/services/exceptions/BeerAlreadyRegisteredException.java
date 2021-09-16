package com.marlon.beerapi.services.exceptions;

public class BeerAlreadyRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BeerAlreadyRegisteredException(String beerName) {
        super(String.format("Beer with name %s already registered in the system.", beerName));
    }
}
