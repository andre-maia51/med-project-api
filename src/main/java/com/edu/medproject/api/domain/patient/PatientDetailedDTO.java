package com.edu.medproject.api.domain.patient;

import com.edu.medproject.api.domain.address.Address;

public record PatientDetailedDTO(Long id, String name, String email, String telephone, String cpf, Address address){
    public PatientDetailedDTO(Patient data) {
        this(data.getId(), data.getName(), data.getEmail(), data.getTelephone(), data.getCpf(), data.getAddress());
    }
}
