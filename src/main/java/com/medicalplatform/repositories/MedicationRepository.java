package com.medicalplatform.repositories;

import com.medicalplatform.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {

}
