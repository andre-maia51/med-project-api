package com.edu.medproject.api.domain.appointment.validations.scheduling;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class FunctioningTimeValidator implements MakeAppointmentValidator{
    public void validate(AppointmentDataDTO data) {
        var appointmentDate = data.date();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = appointmentDate.getHour() < 7;
        var afterClosed = appointmentDate.getHour() > 18;

        if(sunday || beforeOpening || afterClosed) {
            throw new ValidationException("A consulta agendada está fora do horário de funcionamento do estabelecimento");
        }
    }
}
