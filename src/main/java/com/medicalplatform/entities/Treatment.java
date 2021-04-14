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
public class Treatment implements Serializable {

    private final static long serialVersionUID = 1l;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Type(type="uuid-binary")
    private UUID id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="startInterval", nullable = false)
    private String startInterval;

    @Column(name="endInterval",nullable = false)
    private String endInterval;

    @ManyToOne(fetch=FetchType.EAGER)
    private Patient patient;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "treatment")
    private List<Procedure> procedureList= new ArrayList<>();

    public List<Procedure> getProcedureList(){return  procedureList;}
    public void setListOfProcedure(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "treatment")
    private List<Medication> medicationList= new ArrayList<>();

    public List<Medication> getMedicationList(){return medicationList;}
    public void setListOfMedication(List<Medication> medicationList) {
        this.medicationList = medicationList;
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

    public String getStartInterval() {
        return startInterval;
    }
    public void setStartInterval(String startInterval) {
        this.startInterval = startInterval;
    }

    public String getEndInterval() {
        return endInterval;
    }
    public void setEndInterval(String endInterval) {
        this.endInterval = endInterval;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }



    public Treatment() {
    }

    public Treatment(UUID id, String name, String startInterval, String endInterval,
                          Patient patient, List<Procedure> procedureList,List<Medication> medicationList){
        this.id = id;
        this.name = name;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.patient = patient;
        this.procedureList=procedureList;
        this.medicationList=medicationList;
    }

    public void addProcedure(Procedure procedure){
        procedureList.add(procedure);
    }
    public void addMedication(Medication medication){
        medicationList.add(medication);
    }

}
