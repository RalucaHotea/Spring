package com.medicalplatform.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicalplatform.dtos.PatientDTO;
import com.medicalplatform.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService PatientService) {
        this.patientService = PatientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PatientDTO patientDTO) {
        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<UUID> updatePatient(@Valid @RequestBody PatientDTO patientDTO) {
        UUID patientID = patientService.update(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.UPGRADE_REQUIRED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID PatientId) {
        PatientDTO dto = patientService.findPatientById(PatientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> deletePatient(@PathVariable("id") UUID PatientId){
        PatientDTO dto = patientService.deletePatientById(PatientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
