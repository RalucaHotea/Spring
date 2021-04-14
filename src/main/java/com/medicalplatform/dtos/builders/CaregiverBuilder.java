package com.medicalplatform.dtos.builders;

import com.medicalplatform.dtos.*;
import com.medicalplatform.entities.*;
import com.medicalplatform.entities.Patient;

import java.util.ArrayList;
import java.util.List;


public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {

        List<PatientDTO> patients = new ArrayList<PatientDTO>();
        for(Patient patient: caregiver.getListOfPatients()){
            PatientDTO patientDTO = PatientBuilder.toPatientDTO(patient);
            patients.add(patientDTO);
        }

        ClinicUserDTO clinicUserDTO = ClinicUserBuilder.toClinicUserDTO(caregiver.getClinicUser());
        return new CaregiverDTO(caregiver.getId(),caregiver.getName(),
                caregiver.getBirthDate(), caregiver.getGender(),caregiver.getAddress(), clinicUserDTO, patients);
    }

    public static Caregiver toEntity(CaregiverDTO caregiverDTO) {

        Caregiver caregiver = new Caregiver();
        caregiver.setId(caregiverDTO.getId());
        caregiver.setName(caregiverDTO.getName());
        caregiver.setBirthDate(caregiverDTO.getDateofbirth());
        caregiver.setGender(caregiverDTO.getGender());
        caregiver.setAddress(caregiverDTO.getAddress());

        List<Patient> patients = new ArrayList<Patient>();
        for(PatientDTO patientDTO: caregiverDTO.getListOfPatients()){
            Patient patient = PatientBuilder.toEntity(patientDTO);
            patients.add(patient);
        }

        caregiver.setListOfPatients(patients);
        return caregiver;
    }

}
