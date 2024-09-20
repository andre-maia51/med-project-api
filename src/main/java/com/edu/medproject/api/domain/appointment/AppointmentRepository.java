package com.edu.medproject.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime firstHour, LocalDateTime lastHour);

    Boolean existsByDoctorIdAndDateAndMotivationIsNull(Long doctorId, LocalDateTime date);
}
