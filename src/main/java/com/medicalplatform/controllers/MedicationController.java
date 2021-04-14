package com.medicalplatform.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicalplatform.dtos.MedicationDTO;
import com.medicalplatform.services.MedicationService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value="/{idTreatment}")
    public ResponseEntity<UUID> insertMedication(@Valid @RequestBody MedicationDTO medicationDTO,@PathVariable("treatmentId") UUID treatmentId) {
        UUID medicationID = medicationService.insert(medicationDTO,treatmentId);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID medicationId) {
        MedicationDTO dto = medicationService.findMedicationById(medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> deleteMedication(@PathVariable("id") UUID medicationId){
        MedicationDTO dto = medicationService.deleteMedicationById(medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
