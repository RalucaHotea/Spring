package com.medicalplatform.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Doctor implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name="phonenumber", nullable = false)
    private String phonenumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private ClinicUser clinicUser;

    public ClinicUser getClinicUser() {
        return clinicUser;
    }
    public void setClinicUser(ClinicUser clinicUser) {
        this.clinicUser = clinicUser;
    }

    public Doctor(UUID id, String name, String gender, String email, String phonenumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phonenumber=phonenumber;
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

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber(){return phonenumber;}
    public void setPhonenumber(String phonenumber){this.phonenumber = phonenumber;}

    public Doctor() {
    }

}