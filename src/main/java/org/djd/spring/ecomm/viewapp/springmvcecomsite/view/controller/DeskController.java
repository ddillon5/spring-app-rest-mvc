package org.djd.spring.ecomm.viewapp.springmvcecomsite.view.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.Desk;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.exception.ItemNotFoundException;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.repos.DeskRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DeskController {
	private final DeskRepository deskRepository;
	private final DeskResourceAssembler assembler;
	
	
	DeskController(DeskRepository repository, DeskResourceAssembler assembler){
		this.deskRepository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/desks")
	Resources<Resource<Desk>> getAll() {

		List<Resource<Desk>> desks = deskRepository.findAll().stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(desks,
			linkTo(methodOn(DeskController.class).getAll()).withSelfRel());
	}
	
	@GetMapping("/desks/{id}")
	Resource<Desk> one(@PathVariable Long id) {
		return assembler.toResource(
			deskRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(id)));
	}
	
	@PostMapping("/desks")
	ResponseEntity<Resource<Desk>> newDesk (@RequestBody Desk desk) {

		
		Desk newDesk = deskRepository.save(desk);

		return ResponseEntity
			.created(linkTo(methodOn(DeskController.class).one(newDesk.getId())).toUri())
			.body(assembler.toResource(newDesk));
	}
	
}
