package com.edu.medproject.api.domain.doctor;

import com.edu.medproject.api.domain.address.AddressDataDTO;
import com.edu.medproject.api.domain.appointment.Appointment;
import com.edu.medproject.api.domain.patient.Patient;
import com.edu.medproject.api.domain.patient.PatientSingupDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver null quando unico médico cadastrado não está disponível na data")
    void getRandomDoctorScenario1() {
        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctor = singUpDoctor("Medico", "medico@example.com", "123456", Specialty.CARDIOLOGIA);
        var patient = signUpPatient("Paciente", "paciente@example", "00000000000");
        singUpAppointment(doctor, patient, nextMonday);

        var freeDoctor = doctorRepository.getRandomDoctor(Specialty.CARDIOLOGIA, nextMonday);
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void getRandomDoctorScenario2() {
        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctor = singUpDoctor("Medico", "medico@example.com", "123456", Specialty.CARDIOLOGIA);

        var freeDoctor = doctorRepository.getRandomDoctor(Specialty.CARDIOLOGIA, nextMonday);
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private void singUpAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(doctor, patient, date));
    }

    private Doctor singUpDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient signUpPatient(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorSingupDataDTO doctorData(String name, String email, String crm, Specialty specialty) {
        return new DoctorSingupDataDTO(name, email, "61999999999", crm, specialty, addressData());
    }

    private PatientSingupDTO patientData(String name, String email, String cpf) {
        return new PatientSingupDTO(name, email, "61999999999", cpf, addressData());
    }

    private AddressDataDTO addressData() {
        return new AddressDataDTO("rua xyz", "bairro", "00000000", "Brasilia", "DF", null, null);
    }
}