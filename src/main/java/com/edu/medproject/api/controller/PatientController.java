package com.edu.medproject.api.controller;

import com.edu.medproject.api.domain.patient.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public ResponseEntity<Page<PatientListDTO>> getListPatient(@PageableDefault(size = 10, page = 0, sort = {"name"})Pageable pageable) {
        var page = patientRepository.findAllByActiveTrue(pageable).map(PatientListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSpecificPatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailedDTO(patient));
    }

    @PostMapping
    @Transactional
    public ResponseEntity postPatient(@RequestBody @Valid PatientSingupDTO data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        patientRepository.save(patient);
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailedDTO(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editPatientInfo(@RequestBody @Valid PatientUpdateDTO data) {
        var patient = patientRepository.getReferenceById(data.id());
        patient.updatePatientInfo(data);
        return ResponseEntity.ok(new PatientDetailedDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.deletePatient();
        return ResponseEntity.noContent().build();
    }

}
