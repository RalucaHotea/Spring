package com.medicalplatform.controllers;


import com.medicalplatform.dtos.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicalplatform.dtos.CaregiverDTO;
import com.medicalplatform.services.CaregiverService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService CaregiverService) {
        this.caregiverService = CaregiverService;
    }

    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<UUID> updateCaregiver(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverID = caregiverService.update(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.UPGRADE_REQUIRED);
    }

    @PostMapping(value = "/addPatient/{idPatient}/{idCaregiver}")
    public ResponseEntity<UUID> addPatient( @PathVariable("idPatient") UUID patientId, @PathVariable("idCaregiver") UUID caregiverId) {
        UUID caregiverID = caregiverService.addPatient(patientId, caregiverId);
        return new ResponseEntity<>(caregiverID, HttpStatus.UPGRADE_REQUIRED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID CaregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(CaregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> deleteCaregiver(@PathVariable("id") UUID CaregiverId){
        CaregiverDTO dto = caregiverService.deleteCaregiverById(CaregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
//
//    @PutMapping(value = "{id}")
//    public ResponseEntity<CaregiverDTO> updateCaregiver(@PathVariable("id") UUID CaregiverId){
//        CaregiverDTO dto = CaregiverService.updateCaregiverById(CaregiverId);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }

    //TODO: UPDATE, DELETE per resource

}
