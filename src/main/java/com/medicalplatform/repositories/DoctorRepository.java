package com.medicalplatform.repositories;

import com.medicalplatform.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID>{

}
