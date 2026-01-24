package com.v1.medi_report.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.medi_report.entity.AppUser;


public interface AppUsersRepository extends JpaRepository<AppUser, Long> {

	boolean existsByUsername(String email);

	Optional<AppUser> getByUsername(String username);

}
