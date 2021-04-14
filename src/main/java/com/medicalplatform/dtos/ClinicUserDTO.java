package com.medicalplatform.dtos;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ClinicUserDTO {

    private UUID id;
    private String username;
    private String password;
    private String type;
    private PatientDTO patientDTO;
    private DoctorDTO doctorDTO;
    private CaregiverDTO caregiverDTO;

    public ClinicUserDTO(UUID id, String username, String password, String type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public ClinicUserDTO() {
    }

    public PatientDTO getPatientDTO() {
        return patientDTO;
    }
    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    public DoctorDTO getDoctorDTO() {
        return doctorDTO;
    }

    public void setDoctorDTO(DoctorDTO doctorDTO) {
        this.doctorDTO = doctorDTO;
    }

    public CaregiverDTO getCaregiverDTO() {
        return caregiverDTO;
    }

    public void setCaregiverDTO(CaregiverDTO caregiverDTO) {
        this.caregiverDTO = caregiverDTO;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {//checking if both the object references are referring to the same object
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ClinicUserDTO that = (ClinicUserDTO) object;//type casting
        return Objects.equals(id, that.id) &&// compara campurile obiectului pe care noi il verificam
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }//atunci cand avem un obiect cu multe variabile , folosim functia hash si se foloseste impreuna cu metoda hashcode
}
