package com.edu.medproject.api.domain.appointment;

import com.edu.medproject.api.domain.appointment.validations.cancel.CancelAppointmentValidator;
import com.edu.medproject.api.domain.appointment.validations.scheduling.MakeAppointmentValidator;
import com.edu.medproject.api.domain.doctor.Doctor;
import com.edu.medproject.api.domain.doctor.DoctorRepository;
import com.edu.medproject.api.domain.patient.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<MakeAppointmentValidator> makeAppointmentValidators;

    @Autowired
    private List<CancelAppointmentValidator> cancelAppointmentValidators;

    public AppointmentDetailedDTO makeAppointment(AppointmentDataDTO data) {
        if(!patientRepository.existsById(data.patientId())) {
            throw new ValidationException("O id do paciente não existe.");
        }

        if(data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidationException("O id do médico não existe.");
        }

        makeAppointmentValidators.forEach(v -> v.validate(data));

        var doctor = getDoctor(data);
        if(doctor == null) {
            throw new ValidationException("Não há médico disponível nesta data.");
        }
        var patient = patientRepository.getReferenceById(data.patientId());
        var appointment = new Appointment(doctor, patient, data.date());
        appointmentRepository.save(appointment);

        return new AppointmentDetailedDTO(appointment);
    }

    public void cancelAppointment(AppointmentCancelledDTO data) {
        if(!appointmentRepository.existsById(data.appointmentId())) {
            throw new ValidationException("O id da consulta informado não existe.");
        }

        cancelAppointmentValidators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        appointment.cancel(data.cancelMotivation());
    }

    private Doctor getDoctor(AppointmentDataDTO data) {
        if(data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if(data.specialty() == null) {
            throw new ValidationException("O campo especialidade deve estar preenchido quando não há médico selecionado.");
        }

        Pageable pageable = PageRequest.of(0, 1);
        return doctorRepository.getRandomDoctor(data.specialty(), data.date(), pageable);
    }
}
