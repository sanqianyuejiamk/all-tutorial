package com.mengka.springboot.builder_01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Customer 
{
	private Long id;
	private String firstName;
	private String lastName;
	private String birthdate;
}