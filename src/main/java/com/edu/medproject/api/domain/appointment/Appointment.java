package com.edu.medproject.api.domain.appointment;

import com.edu.medproject.api.domain.doctor.Doctor;
import com.edu.medproject.api.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Appointment")
@Table(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime date;

    @Column(name = "cancel_motivation")
    @Enumerated(EnumType.STRING)
    private CancelMotivation motivation;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public void cancel(CancelMotivation motivation) {
        this.motivation = motivation;
    }
}
