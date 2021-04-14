package com.medicalplatform.services;

import com.medicalplatform.dtos.MedicationDTO;
import com.medicalplatform.dtos.ProcedureDTO;
import com.medicalplatform.dtos.TreatmentDTO;
import com.medicalplatform.dtos.builders.MedicationBuilder;
import com.medicalplatform.dtos.builders.ProcedureBuilder;
import com.medicalplatform.dtos.builders.TreatmentBuilder;
import com.medicalplatform.entities.*;
import com.medicalplatform.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TreatmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentService.class);
    private final TreatmentRepository treatmentRepository;
    private final MedicationRepository medicationRepository;
    private final ProcedureRepository procedureRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository,
                            MedicationRepository medicationRepository,
                            PatientRepository patientRepository,
                            DoctorRepository doctorRepository,ProcedureRepository procedureRepository) {
        this.treatmentRepository = treatmentRepository;
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.procedureRepository=procedureRepository;
    }

    public List<TreatmentDTO> findMedicationPlans() {
        List<Treatment> treatmentList = treatmentRepository.findAll();
        List<TreatmentDTO> medicationPlanDTOList =  treatmentList.stream()
                .map(TreatmentBuilder::toTreatmentDTO)
                .collect(Collectors.toList());
        return medicationPlanDTOList;
    }

    public TreatmentDTO findTreatmentById(UUID id) {
        Optional<Treatment> prosumerOptional = treatmentRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("MedicationPlan with id {} was not found in db", id);
            throw new ResourceNotFoundException(Treatment.class.getSimpleName() + " with id: " + id);
        }
        return TreatmentBuilder.toTreatmentDTO(prosumerOptional.get());
    }

    public TreatmentDTO deleteTreatmentById(UUID id){
        Optional<Treatment> prosumerOptional = treatmentRepository.findById(id);
        treatmentRepository.deleteById(id);


        if (!prosumerOptional.isPresent()) {
            LOGGER.error("MedicationPlan with id {} was not found in db", id);
            throw new ResourceNotFoundException(Treatment.class.getSimpleName() + " with id: " + id);
        }
        return TreatmentBuilder.toTreatmentDTO(prosumerOptional.get());
    }

    public UUID insert(TreatmentDTO treatmentDTO, UUID idPatient) {

        Treatment treatment = TreatmentBuilder.toEntity(treatmentDTO);
        Optional<Patient> prosumerOptional = patientRepository.findById(idPatient);
        treatment.setPatient(prosumerOptional.get());
        treatment = treatmentRepository.save(treatment);
        LOGGER.debug("Treatment with id {} was inserted in db", treatment.getId());


        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", idPatient);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + idPatient);
        }
        prosumerOptional.get().addTreatment(treatment);


        treatment = treatmentRepository.save(treatment);
        return treatment.getId();
    }

    public UUID addTreatment(MedicationDTO medicationDTO, ProcedureDTO procedureDTO, UUID treatmentId){

        Optional<Treatment> treatmentOptional = treatmentRepository.findById(treatmentId);
        if (!treatmentOptional.isPresent()) {
            LOGGER.error("Treatment with id {} was not found in db", treatmentId);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + treatmentId);
        }
        Medication medication= MedicationBuilder.toEntity(medicationDTO);
        Procedure procedure= ProcedureBuilder.toEntity(procedureDTO);
        treatmentOptional.get().addMedication(medication);
        treatmentOptional.get().addProcedure(procedure);
        Treatment treatment = treatmentRepository.save(treatmentOptional.get());

        return treatment.getId();
    }
}

