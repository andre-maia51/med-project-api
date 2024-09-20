package com.edu.medproject.api.domain.appointment.validations.cancel;

import com.edu.medproject.api.domain.appointment.AppointmentCancelledDTO;

public interface CancelAppointmentValidator {
    void validate(AppointmentCancelledDTO data);
}
