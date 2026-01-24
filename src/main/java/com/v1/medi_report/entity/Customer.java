package com.v1.medi_report.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id ;
	@Column(nullable = false )
	private String name;
	
	@Column(nullable = false , unique=true)
	private String contactNumber;

}
