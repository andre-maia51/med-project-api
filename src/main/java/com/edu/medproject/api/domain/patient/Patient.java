package com.edu.medproject.api.domain.patient;

import com.edu.medproject.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Patient")
@Table(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String telephone;
    private String cpf;

    @Embedded
    private Address address;
    private Boolean active;

    public Patient(PatientSingupDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updatePatientInfo(PatientUpdateDTO data) {
        if(data.name() != null) {this.name = data.name();}
        if(data.telephone() != null) {this.telephone = data.telephone();}
        if(data.address() != null) {this.address.updateInfoAdress(data.address());}
    }

    public void deletePatient() {
        this.active = false;
    }

}
