package com.marlon.beerapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.marlon.beerapi.entities.Beer;

public class QuantityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
    @Max(100)
    private Integer quantity;
    
    public QuantityDTO() {}

	public QuantityDTO(Integer quantity) {
		this.quantity = quantity;
	}
    
    public QuantityDTO(Beer entity) {
    	quantity = entity.getQuantity();
    }

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
