package com.medicalplatform.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.Id;
import javax.persistence.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class ClinicUser implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy="uuid2")
    @Type(type="uuid-binary")
    private UUID Id;

    @Column(name="username",nullable=false )
    private String username;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="type", nullable = false)
    private String type;

    @OneToOne(mappedBy="clinicUser",cascade= CascadeType.REMOVE)
    private Patient patient;

    @OneToOne(mappedBy="clinicUser", cascade=CascadeType.REMOVE)
    private Caregiver caregiver;

    @OneToOne(mappedBy = "clinicUser",fetch= FetchType.EAGER, cascade=CascadeType.REMOVE)
    private Doctor doctor;

    public ClinicUser(UUID Id , String username, String password, String type){
        this.Id=Id;
        this.username=username;
        this.password=password;
        this.type=type;
    }

    public UUID getId(){ return Id;
    }
    public void setId(UUID Id){ this.Id=Id;
    }

    public String getUsername(){ return username;
    }
    public void setUsername(String username){ this.username=username;
    }

    public String getPassword() { return password;
    }
    public void setPassword(String password) {this.password = password; }

    public String getType(){ return type;
    }
    public void setType(String type){
        this.type= type;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }
    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public ClinicUser() {
    }





}
