package com.v1.medi_report.service;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.v1.medi_report.entity.AppUser;
import com.v1.medi_report.exception.NotFoundException;
import com.v1.medi_report.repository.AppUsersRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final AppUsersRepository userRepo;

	public CustomUserDetailsService(AppUsersRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {

		AppUser existingUser = userRepo.getByUsername(username);
		if (Objects.isNull(existingUser)) {
			throw new NotFoundException("user not found");
		}
		return new CustomUserDetails(existingUser);
	}

}
