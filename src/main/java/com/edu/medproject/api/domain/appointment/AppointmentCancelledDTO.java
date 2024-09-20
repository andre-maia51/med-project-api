package com.edu.medproject.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record AppointmentCancelledDTO(
        @JsonAlias("id")
        @NotNull
        Long appointmentId,

        @JsonAlias("motivo")
        @NotNull
        CancelMotivation cancelMotivation
) {
}
