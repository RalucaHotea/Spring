package com.medicalplatform.services;

import com.medicalplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import com.medicalplatform.dtos.PatientDTO;
import com.medicalplatform.dtos.builders.ClinicUserBuilder;
import com.medicalplatform.dtos.builders.PatientBuilder;
import com.medicalplatform.entities.ClinicUser;
import com.medicalplatform.entities.Patient;
import com.medicalplatform.repositories.ClinicUserRepository;
import com.medicalplatform.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final ClinicUserRepository clinicUserRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, ClinicUserRepository clinicUserRepository) {
        this.patientRepository = patientRepository;
        this.clinicUserRepository = clinicUserRepository;
    }
    public List<PatientDTO> findPatients() {
        List<Patient> PatientList = patientRepository.findAll();
        return PatientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public PatientDTO deletePatientById(UUID id){
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        patientRepository.deleteById(id);

        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public UUID insert(PatientDTO patientDTO) {
        ClinicUser clinicUser = ClinicUserBuilder.toEntity(patientDTO.getClinicUser());
        clinicUser = clinicUserRepository.save(clinicUser);

        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient.setClinicUser(clinicUser);

        patient = patientRepository.save(patient);
        LOGGER.debug("Doctor with id {} was inserted in db", patient.getId());
        return patient.getId();
    }

    public UUID update(PatientDTO patientDTO){
        Patient patient = PatientBuilder.toEntity(patientDTO);

        PatientDTO patientDTO1 = this.findPatientById(patientDTO.getId());

        ClinicUser clinicUser = ClinicUserBuilder.toEntity(patientDTO1.getClinicUser());
        patient.setClinicUser(clinicUser);
        patient = patientRepository.save(patient);

        LOGGER.debug("Patient with id {} was updated in db", patient.getId());
        return patient.getId();
    }
}