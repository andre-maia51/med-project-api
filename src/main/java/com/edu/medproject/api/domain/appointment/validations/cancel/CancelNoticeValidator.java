package com.edu.medproject.api.domain.appointment.validations.cancel;

import com.edu.medproject.api.domain.appointment.AppointmentCancelledDTO;
import com.edu.medproject.api.domain.appointment.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelNoticeValidator implements CancelAppointmentValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentCancelledDTO data) {
        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        var now = LocalDateTime.now();
        var diffInHours = Duration.between(now, appointment.getDate()).toHours();

        if(diffInHours < 24) {
            throw new ValidationException("Para a consulta ser cancelada, deve ter antecedência mínima de 24 horas.");
        }
    }
}
