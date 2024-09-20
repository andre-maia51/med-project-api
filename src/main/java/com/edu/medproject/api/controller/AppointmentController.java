package com.edu.medproject.api.controller;

import com.edu.medproject.api.domain.appointment.AppointmentCancelledDTO;
import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import com.edu.medproject.api.domain.appointment.AppointmentDetailedDTO;
import com.edu.medproject.api.domain.appointment.AppointmentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity makeAppointment(@RequestBody @Valid AppointmentDataDTO data) {
        var appointmentDTO = appointmentService.makeAppointment(data);
        return ResponseEntity.ok(appointmentDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelAppointment(@RequestBody @Valid AppointmentCancelledDTO data) {
        appointmentService.cancelAppointment(data);
        return ResponseEntity.noContent().build();
    }

}
