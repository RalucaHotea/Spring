package com.medicalplatform.services;

import com.medicalplatform.dtos.ClinicUserDTO;
import com.medicalplatform.dtos.builders.*;
import com.medicalplatform.entities.ClinicUser;
import com.medicalplatform.entities.Patient;
import com.medicalplatform.repositories.ClinicUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClinicUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicUserService.class);
    private final ClinicUserRepository clientUserRepository;

    @Autowired
    public ClinicUserService(ClinicUserRepository clinicUserRepository) {
        this.clientUserRepository = clinicUserRepository;
    }

    public List<ClinicUserDTO> findUsers(){
        List<ClinicUser> clinicUserList = clientUserRepository.findAll();
        return clinicUserList.stream()
                .map(ClinicUserBuilder::toClinicUserDTO)
                .collect(Collectors.toList());
    }

    public ClinicUserDTO findClinicUserById(UUID id){
        Optional<ClinicUser> prosumerOptional = clientUserRepository.findById(id);
        if(!prosumerOptional.isPresent()){
            LOGGER.error("Clinic user with id {} was not found in db", id);
            throw new ResourceNotFoundException(ClinicUser.class.getSimpleName() + " with id: " + id);
        }
        return ClinicUserBuilder.toClinicUserDTO(prosumerOptional.get());
    }

    public ClinicUserDTO findClinicUser(ClinicUserDTO clinicUserDTO){
        Optional<ClinicUser> prosumerOptional = clientUserRepository.findUserByUsername(clinicUserDTO.getUsername(), clinicUserDTO.getPassword());
        if(!prosumerOptional.isPresent()){
            LOGGER.error("Clinic user with username {} was not found in db", clinicUserDTO.getUsername());
            throw new ResourceNotFoundException(ClinicUser.class.getSimpleName() + " with id: " + clinicUserDTO.getUsername());
        }

        ClinicUserDTO clinicUserDTO1 = ClinicUserBuilder.toClinicUserDTO(prosumerOptional.get());

        if(clinicUserDTO1.getType().equals("DOCTOR")){
            clinicUserDTO1.setDoctorDTO(DoctorBuilder.toDoctorDTO(prosumerOptional.get().getDoctor()));
        }
        else if(clinicUserDTO1.getType().equals("PATIENT")){
            clinicUserDTO1.setPatientDTO(PatientBuilder.toPatientDTO(prosumerOptional.get().getPatient()));
        }
        else if(clinicUserDTO1.getType().equals("CAREGIVER")){
            clinicUserDTO1.setCaregiverDTO(CaregiverBuilder.toCaregiverDTO(prosumerOptional.get().getCaregiver()));
        }

        return clinicUserDTO1;
    }

    public UUID insert(ClinicUserDTO clinicUserDTO){
        ClinicUser clinicUser = ClinicUserBuilder.toEntity(clinicUserDTO);
        clinicUser = clientUserRepository.save(clinicUser);
        LOGGER.debug("Person with id {} was inserted in db", clinicUser.getId());
        return clinicUser.getId();
    }

    public ClinicUserDTO deleteClinicUserById(UUID id){
        Optional<ClinicUser> prosumerOptional = clientUserRepository.findById(id);
        clientUserRepository.deleteById(id);

        if (!prosumerOptional.isPresent()) {
            LOGGER.error("ClinicUser with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }

        return ClinicUserBuilder.toClinicUserDTO(prosumerOptional.get());
    }
}
