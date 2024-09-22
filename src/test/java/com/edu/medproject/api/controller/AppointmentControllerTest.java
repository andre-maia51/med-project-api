package com.edu.medproject.api.controller;

import com.edu.medproject.api.domain.appointment.AppointmentDataDTO;
import com.edu.medproject.api.domain.appointment.AppointmentDetailedDTO;
import com.edu.medproject.api.domain.appointment.AppointmentService;
import com.edu.medproject.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class AppointmentControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentDataDTO> appointmentDataDTOJson;

    @Autowired
    private JacksonTester<AppointmentDetailedDTO> appointmentDetailedDTOJson;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Deveria devolver o código HTTP 400 quando informações estão inválidas")
    void bookScenario1() throws Exception {
        var response = mvc.perform(post("/appointment"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver o código HTTP 200 quando informações estão válidas")
    void bookScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var speacialty = Specialty.CARDIOLOGIA;

        var detailedData = new AppointmentDetailedDTO(null, 2L, 5L, date);
        when(appointmentService.makeAppointment(any())).thenReturn(detailedData);

        var response = mvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentDataDTOJson.write(
                                        new AppointmentDataDTO(2L, 5L, date, speacialty)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var expectedJson = appointmentDetailedDTOJson.write(
                detailedData
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}