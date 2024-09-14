package com.edu.medproject.api.controller;

import com.edu.medproject.api.domain.doctor.*;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> getListDoctor(@PageableDefault(size = 10, page = 0, sort = {"name"}) Pageable pageable) {
        var page = doctorRepository.findAllByActiveTrue(pageable).map(DoctorListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSpecificDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailedDTO(doctor));
    }

    @PostMapping
    @Transactional
    public ResponseEntity postDoctor(@RequestBody @Valid DoctorSingupDataDTO data, UriComponentsBuilder uriBuilder) {
        var newDoctor = new Doctor(data);
        doctorRepository.save(newDoctor);

        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(newDoctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailedDTO(newDoctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editDoctorInfo(@RequestBody @Valid DoctorUpdateDataDTO data) {
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateDoctorInfo(data);

        return ResponseEntity.ok(new DoctorDetailedDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.deleteDoctor();

        return ResponseEntity.noContent().build();
    }
}
