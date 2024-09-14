package com.edu.medproject.api.domain.doctor;

import com.edu.medproject.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

@Table(name="doctors")
@Entity(name="Doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String crm;
    private String telephone;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(DoctorSingupDataDTO dataDTO) {
        this.name = dataDTO.name();
        this.email = dataDTO.email();
        this.telephone = dataDTO.telephone();
        this.crm = dataDTO.crm();
        this.specialty = dataDTO.specialty();
        this.address = new Address(dataDTO.adress());
        this.active = true;
    }

    public void updateDoctorInfo(DoctorUpdateDataDTO data) {
        if(data.name() != null) {this.name = data.name();}
        if(data.email() != null) {this.email = data.email();}
        if(data.telephone() != null) {this.telephone = data.telephone();}
        if(data.addressDataDTO() != null) {this.address.updateInfoAdress(data.addressDataDTO());}
    }

    public void deleteDoctor() {
        this.active = false;
    }
}
