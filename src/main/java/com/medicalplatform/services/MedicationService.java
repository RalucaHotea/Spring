package com.medicalplatform.services;

import com.medicalplatform.dtos.MedicationDTO;
import com.medicalplatform.dtos.builders.MedicationBuilder;
import com.medicalplatform.entities.Treatment;
import com.medicalplatform.repositories.MedicationRepository;
import com.medicalplatform.repositories.TreatmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import com.medicalplatform.entities.Medication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationRepository medicationRepository;
    private final TreatmentRepository treatmentRepository;


    @Autowired
    public MedicationService(MedicationRepository medicatonRepository, TreatmentRepository treatmentRepository) {
        this.medicationRepository = medicatonRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public List<MedicationDTO> findMedications() {
        List<Medication> medicatonList = medicationRepository.findAll();
        return medicatonList.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public MedicationDTO deleteMedicationById(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        medicationRepository.deleteById(id);

        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());//returnam inregistrarea coresp. id-ului dat
    }

    public UUID insert(MedicationDTO medicationDTO, UUID treatmentId) {

        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        Optional<Treatment>prosumerOptional=treatmentRepository.findById(treatmentId);
        if(!prosumerOptional.isPresent()){
            LOGGER.error("Treatment with id "+treatmentId+"was not found!");
            throw  new ResourceNotFoundException(Treatment.class.getSimpleName()+"with id"+ treatmentId);
        }
        medication.setTreatment(prosumerOptional.get());
        prosumerOptional.get().addMedication(medication);
        medication = medicationRepository.save(medication);
        LOGGER.debug("Medication with id {} was inserted in db", medication.getId());
        return medication.getId();
    }
}