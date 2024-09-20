package com.edu.medproject.api.domain.appointment;

import com.edu.medproject.api.domain.doctor.Specialty;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDataDTO(
        @NotNull
        Long patientId,

        Long doctorId,

        @JsonAlias("data")
        @NotNull
        @Future
        LocalDateTime date,

        @JsonAlias("especialidade")
        Specialty specialty) {
}
