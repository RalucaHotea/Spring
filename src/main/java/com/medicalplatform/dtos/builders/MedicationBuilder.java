package com.medicalplatform.dtos.builders;

import com.medicalplatform.dtos.MedicationDTO;
import com.medicalplatform.entities.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationBuilder {

    private MedicationBuilder() {
    }

    public static MedicationDTO toMedicationDTO(Medication medication){

        return new MedicationDTO(medication.getId(), medication.getName(), medication.getDosage(),
                                medication.getSideEffects());
    }

    public static Medication toEntity(MedicationDTO medicationDTO) {

        Medication medication = new Medication();
        medication.setId(medicationDTO.getId());
        medication.setName(medicationDTO.getName());
        medication.setDosage(medicationDTO.getDosage());
        medication.setSideEffects(medicationDTO.getSideEffects());

        return medication;
    }

}
