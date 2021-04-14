package com.medicalplatform.controllers;

import com.medicalplatform.dtos.MedicationDTO;
import com.medicalplatform.dtos.ProcedureDTO;
import com.medicalplatform.dtos.TreatmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicalplatform.services.TreatmentService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/treatment")
public class TreatmentController {

    private final TreatmentService treatmentService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping()
    public ResponseEntity<List<TreatmentDTO>> getTreatment() {
        List<TreatmentDTO> dtos = treatmentService.findMedicationPlans();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/{idPatient}")
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody TreatmentDTO treatmentDTO, @PathVariable("idPatient") UUID idPatient) {
        UUID treatmentID = treatmentService.insert(treatmentDTO, idPatient);
        return new ResponseEntity<>(treatmentID, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{idTreatment}")
    public ResponseEntity<UUID> addTreatment(@Valid @RequestBody MedicationDTO medicationDTO, @Valid @RequestBody ProcedureDTO procedureDTO, @PathVariable("idTreatment") UUID idTreatment) {
        UUID treatmentID = treatmentService.addTreatment(medicationDTO, procedureDTO, idTreatment);
        return new ResponseEntity<>(treatmentID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TreatmentDTO> getMedicationPlan(@PathVariable("id") UUID treatmentId) {
        TreatmentDTO dto = treatmentService.findTreatmentById(treatmentId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TreatmentDTO> deleteMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        TreatmentDTO dto = treatmentService.deleteTreatmentById(medicationPlanId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
