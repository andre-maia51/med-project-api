package com.edu.medproject.api.domain.appointment.validations.scheduling;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;

public interface MakeAppointmentValidator {
    void validate(AppointmentDataDTO data);
}
