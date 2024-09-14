package com.edu.medproject.api.domain.doctor;

import com.edu.medproject.api.domain.address.AddressDataDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDataDTO(
        @NotNull
        Long id,
        @JsonAlias("name")
        String name,
        @JsonAlias("telefone")
        String telephone,
        @JsonAlias("email")
        String email,
        @JsonAlias("endereco")
        AddressDataDTO addressDataDTO) {
}
