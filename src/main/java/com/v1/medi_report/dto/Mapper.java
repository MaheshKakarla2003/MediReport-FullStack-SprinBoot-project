package com.v1.medi_report.dto;



import com.v1.medi_report.entity.AppUser;
import com.v1.medi_report.entity.Customer;
import com.v1.medi_report.entity.Document;
import com.v1.medi_report.entity.Hospital;
import com.v1.medi_report.entity.Patient;
import com.v1.medi_report.entity.Visit;

public class Mapper {

	
	// Helper: map entity -> DTO
	
	 // ---------- Document ----------
    public static Document toDocumentEntity(DocumentUploadRequest req, Visit visit, String storedFilePath, String fileType){
        return Document.builder()
                .visit(visit)
                .patient(visit.getPatient())
                .hospital(visit.getHospital())
                .docType(req.getDocType())
                .description(req.getDescription())
                .fileName(req.getFileName())
                .reportDate(req.getReportDate())
                .filePath(storedFilePath)
                .fileType(fileType)
                .build();
    }
    public static  DocumentSummaryResponse toDocumentSummaryResponse(Document doc) {
    	
    	 return  DocumentSummaryResponse.builder().
    	                                                 id(doc.getId()).
    	                                                 visitId(doc.getVisit().getId()).	                                                 
    	                                                 docType(doc.getDocType())
    	                                                 .description(doc.getDescription()).
    	                                                 fileName(doc.getFileName()).
    	                                                 reportDate(doc.getReportDate())
    	                                                 .patientId(doc.getPatient().getId()).
    	                                                 patientName(doc.getPatient().getFullName()).
    	                                                 hospitalId(doc.getHospital().getId()).
    	                                                 hospitalName(doc.getHospital().getName()).
    	                                                 diseaseName(doc.getVisit().getDiseaseName()).
    	                                                 doctorName(doc.getVisit().getDoctorName()).
    	                                                 build(); 	
    	 
    }
    
    public static  Hospital toHospitalEntity(HospitalRegistrationRequest req) {
        return Hospital.builder()
                .name(req.getHospitalName())
                .email(req.getHospitalEmail())
                .contactNumber(req.getPhone())
                .address(req.getAddress())
                .isActive(true)
                .build();
    }
     
    
     public static  HospitalResponse toHospitalResponse(Hospital hospital) {
        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .contactNumber(hospital.getContactNumber())
                .email(hospital.getEmail())
                .active(hospital.isActive())
                .build();
    }
     
     
     public static  Customer toCustomerEntity(CustomerRegisterRequest req) {
         return Customer.builder()
                 .name(req.getCustomerName())
                 .contactNumber(req.getPhone())
                 .build();
     }
      
     public static  CustomerResponse  toCustomerResponse(Customer req) {
         return CustomerResponse.builder().
        		 id(req.getId())
                 .customerName(req.getName())
                 .contactNumber(req.getContactNumber())
                 .build();
     }
     
     
     
     public static  Patient  toPatientEntity(PatientCreateRequest request) {
         return Patient.builder()
                 .fullName(request.getFullName())
                 .phoneNumber(request.getPhoneNumber())
                 .age(request.getAge())
                 .gender(request.getGender())
                 .hospitalId(request.getHospitalId())
                 .aadhaar(request.getAadhaar())
                 .build();
     }
     
     
     
     
     public static  PatientResponse toPatientResponse(Patient patient) {
         return PatientResponse.builder()
                 .id(patient.getId())
                 .fullName(patient.getFullName())
                 .phoneNumber(patient.getPhoneNumber())
                 .age(patient.getAge())
                 .gender(patient.getGender())
                 .aadhaar(patient.getAadhaar())
                 .hospitalId(patient.getHospitalId())
                 .build();
     }
     
     
     public static Visit toVisitEntity(VisitCreateRequest req, Patient patient, Hospital hospital){
         return Visit.builder()
                 .patient(patient)
                 .hospital(hospital)
                 .diseaseName(req.getDiseaseName())
                 .doctorName(req.getDoctorName())
                 .visitDate(req.getVisitDate())
                 .build();
     }
     
     public static VisitSummaryResponse toVisitSummaryResponse(Visit visit, Long reportCount) {
         return VisitSummaryResponse.builder()
                 .id(visit.getId())
                 .diseaseName(visit.getDiseaseName())
                 .doctorName(visit.getDoctorName())
                 .visitDate(visit.getVisitDate())
                 .patientId(visit.getPatient().getId())
                 .patientName(visit.getPatient().getFullName())
                 .hospitalId(visit.getHospital().getId())
                 .hospitalName(visit.getHospital().getName())
                 .reportCount(reportCount)
                 .build();
     }
     
     public static  AppUserResponse  toAppUserResponse(AppUser request) {
         return AppUserResponse.builder()
                 .id(request.getId())
                 .username(request.getUsername())
                 .password(request.getPassword())
                 .role(request.getRole())
                 .hospitalId(request.getHospital() ==null ? -1 : request.getHospital().getId())
                 .customerId(request.getCustomer() == null ? -1 :request.getCustomer().getId())
                 .build();
     }
     
     
}
