package com.medicalplatform.controllers;


import com.medicalplatform.dtos.ProcedureDTO;
import com.medicalplatform.entities.Procedure;
import com.medicalplatform.services.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/procedure")
public class ProcedureController {

    private final ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping()
    public ResponseEntity<List<ProcedureDTO>> getProcedures() {
        List<ProcedureDTO> dtos = procedureService.findProcedures();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProcedure(@Valid @RequestBody ProcedureDTO procedureDTO) {
        UUID procedureID = procedureService.insert(procedureDTO);
        return new ResponseEntity<>(procedureID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProcedureDTO> getProcedure(@PathVariable("id") UUID procedureId) {
        ProcedureDTO dto = procedureService.findProcedureById(procedureId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProcedureDTO> deleteProcedure(@PathVariable("id") UUID procedureId){
        ProcedureDTO dto = procedureService.deleteProcedureById(procedureId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
