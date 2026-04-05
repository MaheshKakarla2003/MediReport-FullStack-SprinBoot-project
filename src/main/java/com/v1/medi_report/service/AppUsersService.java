package com.v1.medi_report.service;

import com.v1.medi_report.dto.AppUserResponse;
import com.v1.medi_report.dto.CustomerRegisterRequest;
import com.v1.medi_report.dto.CustomerResponse;
import com.v1.medi_report.dto.CustomerUpdateRequest;
import com.v1.medi_report.dto.HospitalRegistrationRequest;
import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.HospitalUpdateRequest;

import jakarta.validation.Valid;

public interface AppUsersService {

	HospitalResponse registerHospital(HospitalRegistrationRequest request);

	AppUserResponse login(String username, String password);

	CustomerResponse registerCustomer(@Valid CustomerRegisterRequest request);

	String deleteUserAccount(Long id);

	CustomerResponse updateCustomer(long customerId, long userId, @Valid CustomerUpdateRequest request);

	HospitalResponse updateHospital(long hospitalId, long userId, @Valid HospitalUpdateRequest request);
}
