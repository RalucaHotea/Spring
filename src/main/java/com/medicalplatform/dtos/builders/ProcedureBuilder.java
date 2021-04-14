package com.medicalplatform.dtos.builders;

import com.medicalplatform.dtos.ProcedureDTO;
import com.medicalplatform.entities.Procedure;

public class ProcedureBuilder {

    private ProcedureBuilder(){

    }

    public static ProcedureDTO toProcedureDTO(Procedure procedure){

        return new ProcedureDTO(procedure.getId(),procedure.getName(),procedure.getNumberOfSession());

    }

    public static Procedure toEntity(ProcedureDTO procedureDTO){

        Procedure procedure= new Procedure();
        procedure.setId(procedureDTO.getId());
        procedure.setName(procedureDTO.getName());
        procedure.setNumberOfSessions(procedureDTO.getNumberOfSession());

        return procedure;
    }

}
