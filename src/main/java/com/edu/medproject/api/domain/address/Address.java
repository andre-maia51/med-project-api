package com.edu.medproject.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String cep;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(AddressDataDTO dataDTO) {
        this.street = dataDTO.street();
        this.neighborhood = dataDTO.neighborhood();
        this.cep = dataDTO.cep();
        this.city = dataDTO.city();
        this.uf = dataDTO.uf();
        this.number = dataDTO.number();
        this.complement = dataDTO.complement();
    }

    public void updateInfoAdress(AddressDataDTO data) {
        if(data.street() != null) {this.street = data.street();}
        if(data.neighborhood() != null) {this.neighborhood = data.neighborhood();}
        if(data.cep() != null) {this.cep = data.cep();}
        if(data.city() != null) {this.city = data.city();}
        if(data.uf() != null) {this.uf = data.uf();}
        if(data.number() != null) {this.number = data.number();}
        if(data.complement() != null) {this.complement = data.complement();}
    }
}
