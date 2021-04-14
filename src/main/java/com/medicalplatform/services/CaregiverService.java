package com.medicalplatform.services;

import com.medicalplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import com.medicalplatform.dtos.CaregiverDTO;
import com.medicalplatform.dtos.builders.ClinicUserBuilder;
import com.medicalplatform.dtos.builders.CaregiverBuilder;
import com.medicalplatform.entities.Caregiver;
import com.medicalplatform.entities.ClinicUser;
import com.medicalplatform.entities.Patient;
import com.medicalplatform.repositories.ClinicUserRepository;
import com.medicalplatform.repositories.CaregiverRepository;
import com.medicalplatform.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;
    private final ClinicUserRepository clinicUserRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository, ClinicUserRepository clinicUserRepository, PatientRepository patientRepository) {
        this.caregiverRepository = caregiverRepository;
        this.clinicUserRepository = clinicUserRepository;
        this.patientRepository = patientRepository;
    }
    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> CaregiverList = caregiverRepository.findAll();
        return CaregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);// afiseaza mesaj in consola pentru a det. unde avem erori
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    public CaregiverDTO deleteCaregiverById(UUID id){
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        caregiverRepository.deleteById(id);

        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    public UUID insert(CaregiverDTO caregiverDTO) {
        ClinicUser clinicUser = ClinicUserBuilder.toEntity(caregiverDTO.getClinicUser());
        clinicUser = clinicUserRepository.save(clinicUser);

        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver.setClinicUser(clinicUser);

        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Doctor with id {} was inserted in db", caregiver.getId());
        return caregiver.getId();
    }

    public UUID update(CaregiverDTO caregiverDTO){
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);

        CaregiverDTO caregiverDTO1 = this.findCaregiverById(caregiverDTO.getId());

        ClinicUser clinicUser = ClinicUserBuilder.toEntity(caregiverDTO1.getClinicUser());
        caregiver.setClinicUser(clinicUser);
        caregiver = caregiverRepository.save(caregiver);

        LOGGER.debug("Caregiver with id {} was updated in db", caregiver.getId());
        return caregiver.getId();
    }

    public UUID addPatient(UUID patientId, UUID id){

        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        Optional<Patient> prosumerOptional2 = patientRepository.findById(patientId);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        prosumerOptional.get().addPatient(prosumerOptional2.get());
        Caregiver caregiver = caregiverRepository.save(prosumerOptional.get());

        return caregiver.getId();
    }
}

