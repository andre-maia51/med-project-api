package com.edu.medproject.api.domain.appointment.validations.scheduling;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import com.edu.medproject.api.domain.patient.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements MakeAppointmentValidator{
    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentDataDTO data) {
        var isPatientActive = patientRepository.findActiveById(data.patientId());
        if(!isPatientActive) {
            throw new ValidationException("O paciente não está ativo no sistema.");
        }
    }
}
