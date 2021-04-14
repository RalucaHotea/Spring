package com.medicalplatform.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Medication implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @Column(name = "sideEffects", nullable = false)
    private String sideEffects;


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="treatment_id")
    private Treatment treatment;

    public Treatment getTreatment() {
        return treatment;
    }
    public void setTreatment(Treatment condition) {
        this.treatment = treatment;
    }

    public Medication(UUID id, String name, String dosage, String sideEffects) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.sideEffects = sideEffects;
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

    public String getDosage() {
        return dosage;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Medication(){

    }

    public String getSideEffects() {
        return sideEffects;
    }
    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }
}