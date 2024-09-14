package com.edu.medproject.api.domain.patient;

import com.edu.medproject.api.domain.address.AddressDataDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDTO(
        @NotNull
        Long id,
        @JsonAlias("nome")
        String name,
        @JsonAlias("telefone")
        String telephone,
        @JsonAlias("endereco")
        AddressDataDTO address) {
}
