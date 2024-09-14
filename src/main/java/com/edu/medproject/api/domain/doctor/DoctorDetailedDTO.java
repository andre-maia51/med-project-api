package com.edu.medproject.api.domain.doctor;

import com.edu.medproject.api.domain.address.Address;


public record DoctorDetailedDTO(Long id, String name, String email, String telephone, String crm, Specialty specialty, Address address) {
    public DoctorDetailedDTO(Doctor data) {
        this(data.getId(), data.getName(), data.getEmail(), data.getTelephone(), data.getCrm(), data.getSpecialty(), data.getAddress());
    }
}
