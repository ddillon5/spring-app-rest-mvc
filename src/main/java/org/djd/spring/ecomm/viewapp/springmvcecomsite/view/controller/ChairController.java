package org.djd.spring.ecomm.viewapp.springmvcecomsite.view.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.Chair;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.exception.ItemNotFoundException;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.repos.ChairRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChairController {
	private final ChairRepository chairRepository;
	private final ChairResourceAssembler assembler;
	
	
	ChairController(ChairRepository repository, ChairResourceAssembler assembler){
		this.chairRepository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/chairs")
	Resources<Resource<Chair>> getAll() {

		List<Resource<Chair>> chairs = chairRepository.findAll().stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(chairs,
			linkTo(methodOn(ChairController.class).getAll()).withSelfRel());
	}
	
	@GetMapping("/chairs/{id}")
	Resource<Chair> one(@PathVariable Long id) {
		return assembler.toResource(
			chairRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(id)));
	}
	
	@PostMapping("/chairs")
	ResponseEntity<Resource<Chair>> newChair (@RequestBody Chair chair) {

		
		Chair newChair = chairRepository.save(chair);

		return ResponseEntity
			.created(linkTo(methodOn(ChairController.class).one(newChair.getId())).toUri())
			.body(assembler.toResource(newChair));
	}
	
	
	
	
}
