package com.medicalplatform.repositories;

import com.medicalplatform.entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TreatmentRepository extends JpaRepository<Treatment, UUID>{

    @Query(value = "SELECT t " +
            "FROM Treatment t ")
    List<Treatment> findTreatment();
}
