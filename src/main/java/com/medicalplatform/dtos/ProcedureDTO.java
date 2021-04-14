package com.medicalplatform.dtos;

import java.util.UUID;

public class ProcedureDTO {

    private UUID id;
    private String name ;
    private int numberOfSessions;

    public ProcedureDTO(UUID id ,String name ,int numberOfSessions){
        this.id=id;
        this.name=name;
        this.numberOfSessions=numberOfSessions;
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

    public ProcedureDTO(){

    }

}
