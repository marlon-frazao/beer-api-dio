package com.marlon.beerapi.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marlon.beerapi.dto.BeerDTO;
import com.marlon.beerapi.dto.QuantityDTO;
import com.marlon.beerapi.services.BeerService;

@RestController
@RequestMapping("api/v1/beers")
public class BeerController {

	@Autowired
	private BeerService service;
	
	@PostMapping
	public ResponseEntity<BeerDTO> create(@RequestBody @Valid BeerDTO dto) {
		dto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<BeerDTO> findByName(@PathVariable String name) {
		return ResponseEntity.ok().body(service.findByName(name));
	}
	
	@GetMapping
	public ResponseEntity<List<BeerDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("{id}/increment")
	public ResponseEntity<BeerDTO> increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantity) {
		return ResponseEntity.ok().body(service.increment(id, quantity.getQuantity()));
	}
	
	@PatchMapping("{id}/decrement")
	public ResponseEntity<BeerDTO> decrement(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantity) {
		return ResponseEntity.ok().body(service.decrement(id, quantity.getQuantity()));
	}
}
