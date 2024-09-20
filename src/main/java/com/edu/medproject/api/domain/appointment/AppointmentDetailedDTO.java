package com.edu.medproject.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailedDTO(Long id, Long patientId, Long doctorId, LocalDateTime date) {
    public AppointmentDetailedDTO(Appointment data) {
        this(data.getId(), data.getPatient().getId(), data.getDoctor().getId(), data.getDate());
    }
}
