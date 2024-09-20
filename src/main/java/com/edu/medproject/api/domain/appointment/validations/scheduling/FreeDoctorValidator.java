package com.edu.medproject.api.domain.appointment.validations.scheduling;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import com.edu.medproject.api.domain.appointment.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FreeDoctorValidator implements MakeAppointmentValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentDataDTO data) {
        var isDoctorOccupied = appointmentRepository.existsByDoctorIdAndDateAndMotivationIsNull(data.doctorId(), data.date());

        if(isDoctorOccupied) {
            throw new ValidationException("O médico escolhido já está com o horário desejado preenchido.");
        }
    }
}
