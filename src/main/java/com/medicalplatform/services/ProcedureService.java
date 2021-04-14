package com.medicalplatform.services;

import com.medicalplatform.dtos.ProcedureDTO;
import com.medicalplatform.dtos.builders.ProcedureBuilder;
import com.medicalplatform.entities.Procedure;
import com.medicalplatform.repositories.ProcedureRepository;
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
public class ProcedureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcedureService.class);
    private final ProcedureRepository procedureRepository;


    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<ProcedureDTO> findProcedures() {
        List<Procedure> procedureList = procedureRepository.findAll();
        return procedureList.stream()
                .map(ProcedureBuilder::toProcedureDTO)
                .collect(Collectors.toList());
    }

    public ProcedureDTO findProcedureById(UUID id) {
        Optional<Procedure> prosumerOptional = procedureRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Procedure with id {} was not found in db", id);
            throw new ResourceNotFoundException(Procedure.class.getSimpleName() + " with id: " + id);
        }
        return ProcedureBuilder.toProcedureDTO(prosumerOptional.get());
    }

    public ProcedureDTO deleteProcedureById(UUID id) {
        Optional<Procedure> prosumerOptional = procedureRepository.findById(id);
        procedureRepository.deleteById(id);

        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(Procedure.class.getSimpleName() + " with id: " + id);
        }
        return ProcedureBuilder.toProcedureDTO(prosumerOptional.get());
    }

    public UUID insert(ProcedureDTO procedureDTO) {
        Procedure procedure = ProcedureBuilder.toEntity(procedureDTO);
        procedure = procedureRepository.save(procedure);
        LOGGER.debug("Medication with id {} was inserted in db", procedure.getId());
        return procedure.getId();
    }
}