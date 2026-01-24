package com.v1.medi_report.entity;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id ;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String phoneNumber;
    private int  age;
    private String gender;
    @Column(nullable = false)
    private long hospitalId; 
    @Column(nullable = false)
    private long aadhaar;

	
	
	
}
