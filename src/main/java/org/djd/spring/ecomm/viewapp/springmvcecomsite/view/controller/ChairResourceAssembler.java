package org.djd.spring.ecomm.viewapp.springmvcecomsite.view.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.Chair;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;



@Component
public class ChairResourceAssembler implements ResourceAssembler<Chair, Resource<Chair>>{

	@Override
	public Resource<Chair> toResource(Chair chair) {
		return new Resource<>(chair,
				linkTo(methodOn(ChairController.class).one(chair.getId())).withSelfRel(),
				linkTo(methodOn(ChairController.class).getAll()).withRel("chairs"));
	}

}
