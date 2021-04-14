package com.medicalplatform.dtos.builders;

import com.medicalplatform.dtos.ClinicUserDTO;
import com.medicalplatform.dtos.PatientDTO;
import com.medicalplatform.dtos.TreatmentDTO;
import com.medicalplatform.entities.*;

import java.util.HashSet;
import java.util.Set;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {

        Set<TreatmentDTO> treatmentPlans = new HashSet<TreatmentDTO>();

        if(patient.getListOfTreatments().size() == 0){

        }
        else{
            for(Treatment treatment: patient.getListOfTreatments()){
                TreatmentDTO treatmentDTO = TreatmentBuilder.toTreatmentDTO(treatment);
                treatmentPlans.add(treatmentDTO);
            }
        }

        ClinicUserDTO clinicUserDTO = new ClinicUserDTO(patient.getClinicUser().getId(),
                patient.getClinicUser().getUsername(), patient.getClinicUser().getPassword(), "PATIENT");

        return new PatientDTO(patient.getId(), patient.getName(), patient.getDateofbirth(),
                    patient.getGender(), patient.getAddress(),patient.getPhoneNumber(), patient.getMedicalRecord(), clinicUserDTO, treatmentPlans);
    }

    public static Patient toEntity(PatientDTO patientDTO) {

        ClinicUser clinicUser = ClinicUserBuilder.toEntity(patientDTO.getClinicUser());

        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setName(patientDTO.getName());
        patient.setDateofbirth(patientDTO.getDateofbirth());
        patient.setGender(patientDTO.getGender());
        patient.setAddress(patientDTO.getAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setMedicalRecord(patientDTO.getMedicalRecord());
        patient.setClinicUser(clinicUser);

        Set<Treatment> treatmentPlans = new HashSet<>();
        if(treatmentPlans.size() > 0){
            for(TreatmentDTO treatmentDTO: patientDTO.getListOfTreatment()){
                Treatment treatmentPlan = TreatmentBuilder.toEntity(treatmentDTO);
                treatmentPlans.add(treatmentPlan);
            }
        }


        patient.setListOfTreatments(treatmentPlans);
        return patient;
    }


}

