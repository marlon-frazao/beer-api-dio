package com.marlon.beerapi.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.marlon.beerapi.entities.Beer;
import com.marlon.beerapi.enums.BeerType;

public class BeerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull
    @Size(min = 1, max = 200)
	private String name;
	
	@NotNull
    @Size(min = 1, max = 200)
	private String brand;
	
	@NotNull
    @Max(500)
	private Integer max;
	
	@NotNull
    @Max(100)
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
    @NotNull
	private BeerType type;
	
	public BeerDTO() {}

	public BeerDTO(Long id, String name, String brand, Integer max, Integer quantity, BeerType type) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.max = max;
		this.quantity = quantity;
		this.type = type;
	}
	
	public BeerDTO(Beer entity) {
		id = entity.getId();
		name = entity.getName();
		brand = entity.getBrand();
		max = entity.getMax();
		quantity = entity.getQuantity();
		type = entity.getType();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BeerType getType() {
		return type;
	}

	public void setType(BeerType type) {
		this.type = type;
	}
	
	public Beer toModel() {
		return new Beer(this);
	}
}
