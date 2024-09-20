package com.edu.medproject.api.domain.appointment.validations.scheduling;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import com.edu.medproject.api.domain.doctor.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements MakeAppointmentValidator {
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(AppointmentDataDTO data) {
        if(data.doctorId() == null) {
            return;
        }

        var isDoctorActive = doctorRepository.findActiveById(data.doctorId());
        if(!isDoctorActive) {
            throw new ValidationException("O médico informado não está ativo no sistema.");
        }
    }
}

