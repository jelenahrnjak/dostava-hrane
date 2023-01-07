package com.fooddelivery.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	String country;
	String city;
	String street;
	Integer number;
	//mozda ce za mape trebati geog sirina i duzina ustvari

}
