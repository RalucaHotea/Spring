package com.medicalplatform.dtos.builders;

import com.medicalplatform.dtos.ClinicUserDTO;
import com.medicalplatform.entities.ClinicUser;

public class ClinicUserBuilder {

    private ClinicUserBuilder() {
    }

    public static ClinicUserDTO toClinicUserDTO(ClinicUser clinicUser) {
        return new ClinicUserDTO(clinicUser.getId(),clinicUser.getUsername(), clinicUser.getPassword(), clinicUser.getType());
    }

    public static ClinicUser toEntity(ClinicUserDTO clinicUserDTO) {

        ClinicUser clinicUser = new ClinicUser();
        clinicUser.setId(clinicUserDTO.getId());
        clinicUser.setUsername(clinicUserDTO.getUsername());
        clinicUser.setType(clinicUserDTO.getType());
        clinicUser.setPassword(clinicUserDTO.getPassword());

        return clinicUser;
    }

}
