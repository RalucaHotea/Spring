package com.medicalplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medicalplatform.entities.Caregiver;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CaregiverRepository extends JpaRepository<Caregiver, UUID> {

}