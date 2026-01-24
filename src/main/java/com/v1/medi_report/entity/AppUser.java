package com.v1.medi_report.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String username; // This will be the email

    @NotBlank
    @Size(min = 8)
    private String password;

    @Column(nullable = false)
    private String role; //  roles: HOSPITAL or CUSTOMER

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "hospital_id")
    private Hospital hospital; // Nullable if it's not a hospital 

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "Customer_id")
    private Customer customer; // Nullable if it's not a customer 
    // Getters and setters
}