package com.edu.medproject.api.domain.appointment.validations.scheduling;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import com.edu.medproject.api.domain.appointment.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacientAlreadyHasAnAppointmentValidator implements MakeAppointmentValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentDataDTO data) {
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var isPacientNotAllowed = appointmentRepository.existsByPatientIdAndDateBetween(data.patientId(), firstHour, lastHour);

        if(isPacientNotAllowed) {
            throw new ValidationException("O paciente já possui uma consulta agendada nesse dia.");
        }
    }
}
