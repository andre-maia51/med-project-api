package com.edu.medproject.api.domain.doctor;

import com.edu.medproject.api.domain.address.AddressDataDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorSingupDataDTO(
        @JsonAlias("nome")
        @NotBlank(message = "{name.required}")
        String name,

        @JsonAlias("email")
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,

        @JsonAlias("telefone")
        @NotBlank(message = "{telephone.required}")
        String telephone,

        @JsonAlias("crm")
        @NotBlank(message = "{crm.required}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalid}")
        String crm,

        @JsonAlias("especialidade")
        @NotNull(message = "{specialty.required}")
        Specialty specialty,

        @JsonAlias("endereco")
        @NotNull(message = "{address.required}")
        @Valid
        AddressDataDTO adress) {
}
