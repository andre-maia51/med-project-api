package com.edu.medproject.api.domain.address;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDataDTO(
        @JsonAlias("logradouro")
        @NotBlank(message = "{street.required}")
        String street,

        @JsonAlias("bairro")
        @NotBlank(message = "{neighborhood.required}")
        String neighborhood,

        @JsonAlias("cep")
        @NotBlank(message = "{cep.required}")
        @Pattern(regexp = "\\d{8}", message = "{cep.invalid}")
        String cep,

        @JsonAlias("cidade")
        @NotBlank(message = "{city.required}")
        String city,

        @JsonAlias("uf")
        @NotBlank(message = "{uf.required}")
        @Pattern(regexp = "[A-Z]{2}", message = "{uf.invalid}")
        String uf,

        @JsonAlias("numero")
        String number,

        @JsonAlias("complemento")
        String complement) {

}
