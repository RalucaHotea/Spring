package com.medicalplatform.repositories;

import com.medicalplatform.entities.Medication;
import com.medicalplatform.entities.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcedureRepository extends JpaRepository<Procedure, UUID> {

}
