package com.medicalplatform.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Procedure implements Serializable {

    private static final long serialVersionUID= 1l;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="numberOfSessions", nullable = false)
    private int numberOfSessions;

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

    public Procedure(UUID id ,String name ,int numberOfSessions){
        this.id=id;
        this.name=name;
        this.numberOfSessions=numberOfSessions;
    }
    public Procedure(){

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

    public int getNumberOfSession() {
        return numberOfSessions;
    }
    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

}
