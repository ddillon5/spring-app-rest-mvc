package org.djd.spring.ecomm.viewapp.springmvcecomsite.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.ColorEnum;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.SizeEnum;

import lombok.Data;

@Data  //lombok to generate getters/setters and tostring
@Entity //jpa
public class Desk implements ShoppingItem{
	private @Id @GeneratedValue Long id;
	private SizeEnum size;
	private ColorEnum color;
	private float price;
	private String description;

	protected Desk() {}
	public Desk(SizeEnum size, ColorEnum color, float price, String description) {
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
