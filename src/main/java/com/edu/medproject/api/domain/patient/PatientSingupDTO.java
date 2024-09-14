package com.edu.medproject.api.domain.patient;

import com.edu.medproject.api.domain.address.AddressDataDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientSingupDTO(
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

        @JsonAlias("cpf")
        @NotBlank(message = "{cpf.requires}")
        @Pattern(regexp = "\\d{11}", message = "{cpf.invalid}")
        String cpf,

        @JsonAlias("endereco")
        @NotNull(message = "{address.requires}") @Valid
        AddressDataDTO address) {
}
