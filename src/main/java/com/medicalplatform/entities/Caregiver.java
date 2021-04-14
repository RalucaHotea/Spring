package com.medicalplatform.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Caregiver implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dateofbirth", nullable = false)
    private String dateofbirth;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Patient> listOfPatients = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private ClinicUser clinicUser;

    public Caregiver(UUID id, String name, String dateOfBirth, String gender, String address, List<Patient> listOfPatients) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.listOfPatients = listOfPatients;
    }

    public ClinicUser getClinicUser() {
        return clinicUser;
    }

    public void setClinicUser(ClinicUser clinicUser) {
        this.clinicUser = clinicUser;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getBirthDate() {
        return dateofbirth;
    }

    public void setBirthDate(String birthDate) {
        this.dateofbirth = birthDate;
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

    public List<Patient> getListOfPatients() {
        return listOfPatients;
    }

    public void setListOfPatients(List<Patient> listOfPatients) {
        this.listOfPatients = listOfPatients;
    }

    public Caregiver() {
    }

    public void addPatient(Patient patient){
        listOfPatients.add(patient);
        patient.setCaregiver(this);
    }
}