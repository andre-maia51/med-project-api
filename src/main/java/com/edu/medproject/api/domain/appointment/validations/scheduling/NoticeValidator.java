package com.edu.medproject.api.domain.appointment.validations.scheduling;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class NoticeValidator implements MakeAppointmentValidator{
    public void validate(AppointmentDataDTO data) {
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var diffInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if(diffInMinutes < 30) {
            throw new ValidationException("A consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
