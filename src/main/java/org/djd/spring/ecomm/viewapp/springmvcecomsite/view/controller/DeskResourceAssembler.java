package org.djd.spring.ecomm.viewapp.springmvcecomsite.view.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.Desk;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

public class DeskResourceAssembler implements ResourceAssembler<Desk, Resource<Desk>>{

	@Override
	public Resource<Desk> toResource(Desk desk) {
		return new Resource<>(desk,
				linkTo(methodOn(DeskController.class).one(desk.getId())).withSelfRel(),
				linkTo(methodOn(DeskController.class).getAll()).withRel("desks"));
	}

}

