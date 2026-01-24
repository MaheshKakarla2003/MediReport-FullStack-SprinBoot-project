package com.v1.medi_report.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Document {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( nullable = false)
    private Visit visit;

	@Column(nullable = false)
	private String docType;
	
	private String description;
	@Column(nullable = false)
	private String filePath;
	private String fileType;
	
	@Column(nullable = false)
	private String fileName;
	
	@Column(nullable = false)
	private LocalDate reportDate;
	
	
}
