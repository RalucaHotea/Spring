package com.medicalplatform.controllers;

import com.medicalplatform.dtos.DoctorDTO;
import com.medicalplatform.services.DoctorService;
import com.medicalplatform.services.ClinicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final ClinicUserService clinicUserService;

    @Autowired
    public DoctorController(DoctorService doctorService, ClinicUserService clinicUserService) {
        this.doctorService = doctorService;
        this.clinicUserService = clinicUserService;
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> getDoctors(){
        List<DoctorDTO> dtos = doctorService.findDoctors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") UUID DoctorId){
        DoctorDTO dto = doctorService.findDoctorById(DoctorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody DoctorDTO doctorDTO) {
        UUID doctorId = doctorService.insert(doctorDTO);
        return new ResponseEntity<>(doctorId, HttpStatus.CREATED);
    }
}
