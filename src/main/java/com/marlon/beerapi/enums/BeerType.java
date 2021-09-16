package com.marlon.beerapi.enums;

public enum BeerType {

	LAGER("Lager"), 
	MALZBIER("Malzbier"),
	WITBIER("Witbier"),
    WEISS("Weiss"),
    ALE("Ale"),
    IPA("IPA"),
    STOUT("Stout");

    private final String description;

	private BeerType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
