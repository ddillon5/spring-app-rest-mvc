package org.djd.spring.ecomm.viewapp.springmvcecomsite.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.ColorEnum;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.SizeEnum;


import lombok.Data;

@Data  //lombok to generate getters/setters and tostring
@Entity //jpa
public class Chair implements ShoppingItem{
	private @Id @GeneratedValue Long id;
	@NotNull
	private SizeEnum size;
	@NotNull
	private ColorEnum color;
	@NotNull
	private float price;
	@NotNull
	@Size(min=1, max=25)
	private String description;

	public Chair() {}
	public Chair(SizeEnum size, ColorEnum color, float price, String description) {
		this.size = size;
		this.color = color;
		this.price = price;
		this.description = description;
	}
	@Override
	public int compareTo(ShoppingItem o) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
