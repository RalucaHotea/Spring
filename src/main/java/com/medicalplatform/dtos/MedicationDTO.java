package com.medicalplatform.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicationDTO {

    private UUID id;
    private String name;
    private String dosage;
    private String sideEffects;

    public MedicationDTO(UUID id, String name, String dosage,
                        String sideEffects) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.sideEffects = sideEffects;
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

    public String getSideEffects() {
        return sideEffects;
    }
    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public MedicationDTO(){

    }
}
