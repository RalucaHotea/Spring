package com.medicalplatform.dtos;

import com.medicalplatform.entities.Medication;
import com.medicalplatform.entities.Procedure;

import java.util.List;
import java.util.UUID;

public class TreatmentDTO {

    private UUID id;
    private String name;
    private String startInterval;
    private String endInterval;
    private List<ProcedureDTO> procedureList;
    private List<MedicationDTO> medicationList;

    public TreatmentDTO(UUID id, String name, String startInterval, String endInterval,List<ProcedureDTO> procedureList,List<MedicationDTO> medicationList) {
        this.id = id;
        this.name = name;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.procedureList = procedureList;
        this.medicationList = medicationList;
    }

    public TreatmentDTO(){

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

    public List<ProcedureDTO> getProcedureList(){return  procedureList;}
    public void setListOfProcedure(List<ProcedureDTO> procedureList) {
        this.procedureList = procedureList;
    }

    public List<MedicationDTO> getMedicationList(){return medicationList;}
    public void setListOfMedication(List<MedicationDTO> medicationList) {
        this.medicationList = medicationList;
    }


}
