package com.medicalplatform.controllers;

import com.medicalplatform.dtos.ClinicUserDTO;
import com.medicalplatform.services.ClinicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/user")
public class ClinicUserController {

    private final ClinicUserService clinicUserService;

    @Autowired
    public ClinicUserController(ClinicUserService clinicUserService) {
        this.clinicUserService = clinicUserService;
    }

    @GetMapping()
    public ResponseEntity<List<ClinicUserDTO>> getClinicUsers(){
        List<ClinicUserDTO> dtos = clinicUserService.findUsers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicUserDTO> getClinicUser(@PathVariable("id") UUID clinicUserId){
        ClinicUserDTO dto = clinicUserService.findClinicUserById(clinicUserId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ClinicUserDTO> getClinicUserByName(@Valid @RequestBody ClinicUserDTO clinicUserDTO){
        ClinicUserDTO dto = clinicUserService.findClinicUser(clinicUserDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody ClinicUserDTO clinicUserDTO) {
        UUID clinicUserId = clinicUserService.insert(clinicUserDTO);
        return new ResponseEntity<>(clinicUserId, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClinicUserDTO> deleteClinicUser(@PathVariable("id") UUID ClinicUserID){
        ClinicUserDTO dto = clinicUserService.deleteClinicUserById(ClinicUserID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
