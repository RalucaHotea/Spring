package com.medicalplatform.repositories;

import com.medicalplatform.entities.ClinicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

public interface ClinicUserRepository extends JpaRepository<ClinicUser, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    Optional<ClinicUser> findByusername(String username);
    Boolean existsClinicUserByusername(String username);//verificam daca exista un client dupa username

    @Query(value = "SELECT c " +
            "FROM ClinicUser c " +
            "WHERE c.username = :username AND c.password = :password")
    Optional<ClinicUser> findUserByUsername(@RequestBody String username, @RequestBody String password);

}