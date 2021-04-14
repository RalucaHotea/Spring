package com.medicalplatform.dtos.builders;

import com.medicalplatform.dtos.*;
import com.medicalplatform.entities.Medication;
import com.medicalplatform.entities.Procedure;
import com.medicalplatform.entities.Treatment;

import java.util.ArrayList;
import java.util.List;

public class TreatmentBuilder {

    public TreatmentBuilder(){

    }
    public static TreatmentDTO toTreatmentDTO(Treatment treatment){

        List<MedicationDTO> medications = new ArrayList<>();
        if(treatment.getMedicationList().size() == 0){
        }
        else{
            for(Medication medication: treatment.getMedicationList()){//for(type variablename: arrayName)
                MedicationDTO medicationDTO = MedicationBuilder.toMedicationDTO(medication);
                medications.add(medicationDTO);
            }

        }
        List<ProcedureDTO> procedures = new ArrayList<>();
        if(treatment.getProcedureList().size() == 0){

        }
        else{
            for(Procedure procedure: treatment.getProcedureList()){
                ProcedureDTO procedureDTO = ProcedureBuilder.toProcedureDTO(procedure);
                procedures.add(procedureDTO);
            }

        }
        return new TreatmentDTO(treatment.getId(), treatment.getName(),
                treatment.getStartInterval(),treatment.getEndInterval() ,procedures,medications);
        }

    public static Treatment toEntity(TreatmentDTO treatmentDTO) {

       Treatment treatment = new Treatment();
        treatment.setId(treatmentDTO.getId());
        treatment.setName(treatmentDTO.getName());
        treatment.setStartInterval(treatmentDTO.getStartInterval());
        treatment.setEndInterval(treatmentDTO.getEndInterval());


       if(treatmentDTO.getMedicationList() == null){

       }
       else {
           List<Medication> medications = new ArrayList<Medication>();

           for (MedicationDTO medicationDTO : treatmentDTO.getMedicationList()) {
               Medication medication = MedicationBuilder.toEntity(medicationDTO);
               medications.add(medication);
           }
           treatment.setListOfMedication(medications);
       }
        if(treatmentDTO.getProcedureList() == null){

        }
        else {
            List<Procedure> procedures = new ArrayList<Procedure>();

            for (ProcedureDTO procedureDTO: treatmentDTO.getProcedureList()) {
                Procedure procedure = ProcedureBuilder.toEntity(procedureDTO);
                procedures.add(procedure);
            }
            treatment.setListOfProcedure(procedures);
        }

        return treatment;
    }

    public static Treatment toEntity1(TreatmentDTO medicationPlanDTO) {

        Treatment treatment = new Treatment();
        treatment.setId(medicationPlanDTO.getId());
        treatment.setName(medicationPlanDTO.getName());
        treatment.setStartInterval(medicationPlanDTO.getStartInterval());
        treatment.setEndInterval(medicationPlanDTO.getEndInterval());
        return treatment;
    }


}
