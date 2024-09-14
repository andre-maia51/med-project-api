package com.edu.medproject.api.domain.patient;

public record PatientListDTO(String name, String email, String cpf) {
    public PatientListDTO(Patient data) {
        this(data.getName(), data.getEmail(), data.getCpf());
    }
}
