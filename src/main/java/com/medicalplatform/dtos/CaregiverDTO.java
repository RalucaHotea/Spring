package com.medicalplatform.dtos;

import com.medicalplatform.entities.Patient;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.*;

public class CaregiverDTO {

    private UUID id;
    private String name;
    private String dateofbirth;
    private String gender;
    private String address;
    private List<PatientDTO> listOfPatients = new ArrayList<PatientDTO>();
    private ClinicUserDTO clinicUser;

    public CaregiverDTO(UUID id, String name, String dateofbirth, String gender, String address, ClinicUserDTO clinicUser, List<PatientDTO> listOfPatients) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.address = address;
        this.clinicUser = clinicUser;
        this.listOfPatients = listOfPatients;
    }

    public CaregiverDTO() {
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }
    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<PatientDTO> getListOfPatients() {
        return listOfPatients;
    }
    public void setListOfPatients(List<PatientDTO> listOfPatients) {
        this.listOfPatients = listOfPatients;
    }

    public ClinicUserDTO getClinicUser() {
        return clinicUser;
    }
    public void setClinicUser(ClinicUserDTO clinicUser) {
        this.clinicUser = clinicUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDTO that = (CaregiverDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateofbirth, that.dateofbirth) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(listOfPatients, that.listOfPatients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateofbirth, gender, address,listOfPatients);
    }
}
