package com.medicalplatform.services;

import com.medicalplatform.dtos.DoctorDTO;
import com.medicalplatform.dtos.builders.ClinicUserBuilder;
import com.medicalplatform.dtos.builders.DoctorBuilder;
import com.medicalplatform.entities.ClinicUser;
import com.medicalplatform.entities.Doctor;
import com.medicalplatform.repositories.ClinicUserRepository;
import com.medicalplatform.repositories.DoctorRepository;
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
public class DoctorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;
    private final ClinicUserRepository clinicUserRepository;

    @Autowired
    public DoctorService(DoctorRepository DoctorRepository, ClinicUserRepository clinicUserRepository) {
        this.doctorRepository = DoctorRepository;
        this.clinicUserRepository = clinicUserRepository;
    }

    public List<DoctorDTO> findDoctors(){
        List<Doctor> DoctorList = doctorRepository.findAll();
        return DoctorList.stream()
                .map(DoctorBuilder::toDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO findDoctorById(UUID id){
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if(!prosumerOptional.isPresent()){
            LOGGER.error("Doctor user with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(prosumerOptional.get());
    }

    public UUID insert(DoctorDTO doctorDTO){
        ClinicUser clinicUser = ClinicUserBuilder.toEntity(doctorDTO.getClinicUser());

        clinicUser = clinicUserRepository.save(clinicUser);

        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor.setClinicUser(clinicUser);

        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db", doctor.getId());
        return doctor.getId();
    }
}
