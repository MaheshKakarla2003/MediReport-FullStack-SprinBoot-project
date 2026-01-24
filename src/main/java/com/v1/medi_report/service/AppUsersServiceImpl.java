package com.v1.medi_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v1.medi_report.dto.AppUserResponse;
import com.v1.medi_report.dto.CustomerRegisterRequest;
import com.v1.medi_report.dto.CustomerResponse;
import com.v1.medi_report.dto.CustomerUpdateRequest;
import com.v1.medi_report.dto.HospitalRegistrationRequest;
import com.v1.medi_report.dto.HospitalResponse;
import com.v1.medi_report.dto.HospitalUpdateRequest;
import com.v1.medi_report.dto.Mapper;
import com.v1.medi_report.entity.AppUser;
import com.v1.medi_report.entity.Customer;
import com.v1.medi_report.entity.Hospital;
import com.v1.medi_report.entity.Visit;
import com.v1.medi_report.repository.AppUsersRepository;
import com.v1.medi_report.repository.CustomerRepository;
import com.v1.medi_report.repository.HospitalRepository;
import com.v1.medi_report.repository.VisitRepository;

import jakarta.validation.Valid;



@Service
public class AppUsersServiceImpl implements AppUsersService {

	@Autowired
	private  AppUsersRepository appUserRepository;
	@Autowired
    private  HospitalRepository hospitalRepository;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private VisitRepository visitRepository;

    @Override
    public HospitalResponse registerHospital(HospitalRegistrationRequest req) {

        if (appUserRepository.existsByUsername(req.getEmail())) {
            throw new IllegalArgumentException(" User Email already registered ! try to login");
        }
        
     // simple duplicate checks
        if (hospitalRepository.existsByName(req.getHospitalName())) {
            throw new IllegalArgumentException("Hospital name already exists");
        }
        if (hospitalRepository.existsByEmail(req.getHospitalEmail())) {
            throw new IllegalArgumentException("Hospital email already exists");
        }
        if (hospitalRepository.existsByContactNumber(req.getPhone())) {
            throw new IllegalArgumentException("Hospital contact number already exists");
        }
        
        Hospital hospital =hospitalRepository.save(Mapper.toHospitalEntity(req));

        AppUser user = AppUser.builder()
                .username(req.getEmail())
                .password(req.getPassword())
                .role("HOSPITAL")
                .hospital(hospital)
                .build();

        appUserRepository.save(user);
        
        return  Mapper.toHospitalResponse(hospital);
    }
    

	@Override
	public CustomerResponse registerCustomer(@Valid CustomerRegisterRequest req) {
		  if (appUserRepository.existsByUsername(req.getEmail())) {
	            throw new IllegalArgumentException(" User Email already registered ! try to login");
	        }
	        
	     // simple duplicate checks
	        if (customerRepo.existsByContactNumber(req.getPhone())) {
	            throw new IllegalArgumentException("Customer contact number already exists ! try to login");
	        }
	        
	        Customer customer=customerRepo.save(Mapper.toCustomerEntity(req));
	        
	        AppUser user = AppUser.builder()
	                .username(req.getEmail())
	                .password(req.getPassword())
	                .role("CUSTOMER")
	                .customer(customer)
	                .build();

	        appUserRepository.save(user);
	        
	        return  Mapper.toCustomerResponse(customer);
	}



	@Override
	public AppUserResponse login(String username, String password) {
	AppUser user=	appUserRepository.getByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid username , try again !! "));
	           if(!user.getPassword().equals(password))
	        	    throw new IllegalArgumentException("Invalid password , try again !!");
		  return Mapper.toAppUserResponse(user);
	}


	@Override
	public String deleteUserAccount(Long id) {
		   AppUser user= appUserRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id , try again !! "));
		   if(user.getRole().equals("HOSPITAL"))
		   {
			   List<Visit> visits = visitRepository.findByHospitalId(user.getHospital().getId());
			    visitRepository.deleteAll(visits);
		   }
		   appUserRepository.delete(user);
		return "Account deleted Successfullly";
	}

	@Override
	public CustomerResponse updateCustomer(long customerId, long userId,@Valid CustomerUpdateRequest req) {

	        AppUser user=	appUserRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Invalid id , try again !! "));
		   Customer customer=customerRepo.findById(customerId).orElseThrow(()->new IllegalArgumentException("Invalid id , try again !! "));
		   	
		  if ( !user.getUsername().equals(req.getEmail())  &&  appUserRepository.existsByUsername(req.getEmail())) {
	            throw new IllegalArgumentException(" User Email already registered ! try different email");
	        }
	        
	     // simple duplicate checks
	        if (  !customer.getContactNumber().equals(req.getPhone()) &&  customerRepo.existsByContactNumber(req.getPhone())) {
	            throw new IllegalArgumentException("Customer contact number already exists ! try  a different one");
	        }
	         user.setUsername(req.getEmail());
	         customer.setName(req.getCustomerName());
	         customer.setContactNumber(req.getPhone());

	        appUserRepository.save(user);
	        
	        return  Mapper.toCustomerResponse(customer);
		
	}


	@Override
	public HospitalResponse updateHospital(long hospitalId, long userId, @Valid HospitalUpdateRequest req) {
		
		  AppUser user=	appUserRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Invalid id , try again !! "));
		   Hospital hospital=hospitalRepository.findById(hospitalId).orElseThrow(()->new IllegalArgumentException("Invalid id , try again !! "));
		   			
		  if ( !user.getUsername().equals(req.getEmail())  &&appUserRepository.existsByUsername(req.getEmail())) {
	            throw new IllegalArgumentException(" User Email already registered ! try  a different one");
	        }
	        
	     // simple duplicate checks
	        if ( !hospital.getName().equals(req.getHospitalName()) && hospitalRepository.existsByName(req.getHospitalName())) {
	            throw new IllegalArgumentException("Hospital name already exists ! try  a different one");
	        }
	        if ( !hospital.getEmail().equals(req.getHospitalEmail())  && hospitalRepository.existsByEmail(req.getHospitalEmail())) {
	            throw new IllegalArgumentException("Hospital email already exists  ! try  a different one");
	        }
	        if ( !hospital.getContactNumber().equals(req.getPhone())  && hospitalRepository.existsByContactNumber(req.getPhone())) {
	            throw new IllegalArgumentException("Hospital contact number already exists  ! try  a different one");
	        }
	        
	           user.setUsername(req.getEmail());
	           hospital.setEmail(req.getHospitalEmail());
	           hospital.setAddress(req.getAddress());
	           hospital.setContactNumber(req.getPhone());
	           hospital.setName(req.getHospitalName());

	        appUserRepository.save(user);
	        
	        return  Mapper.toHospitalResponse(hospital);
	}



	
}
