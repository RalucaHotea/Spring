package com.medicalplatform.dtos.builders;

import com.medicalplatform.dtos.*;
import com.medicalplatform.entities.ClinicUser;
import com.medicalplatform.entities.Doctor;

public class DoctorBuilder {

    private DoctorBuilder() {
    }

    public static DoctorDTO toDoctorDTO(Doctor doctor) {

        ClinicUserDTO clinicUserDTO = ClinicUserBuilder.toClinicUserDTO(doctor.getClinicUser());
        return new DoctorDTO(doctor.getId(),doctor.getName(), doctor.getGender(), doctor.getEmail(),
                doctor.getPhonenumber(), clinicUserDTO);
    }
    public static Doctor toEntity(DoctorDTO doctorDTO) {

        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setName(doctorDTO.getName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setGender(doctorDTO.getGender());
        doctor.setPhonenumber(doctorDTO.getPhonenumber());

        return doctor;
    }

}
