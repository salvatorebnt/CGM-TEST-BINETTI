package com.cgm.manager.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.model.patient.PatientData;
import com.cgm.manager.service.PatientsService;
import com.cgm.manager.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientsController.class)
public class PatientsControllerMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientsService service;

    @Test
    public void findAllTest()
      throws Exception {
        Patient p = new Patient(1, "Paolo", "Rossi", "01-12-1988", "PPLRSS89R03A69C");
        List<Patient> allPatients = Arrays.asList(p);
        given(service.findAll()).willReturn(allPatients);

        mvc.perform(get("/api/patients")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].name", is(p.getName())));
    }
    
    @Test
    public void findByIdTest()
      throws Exception {
    	long id = 1;
        Patient p = new Patient(id, "Paolo", "Rossi", "01-12-1988", "PPLRSS89R03A69C");
        given(service.findById(id)).willReturn(p);
        mvc.perform(get("/api/patients/" + id)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is(p.getName())))
          .andExpect(jsonPath("$.surname", is(p.getSurname())));
    }
    
    @Test
    public void insertTest() throws Exception {
    	PatientData pd = new PatientData("Paolo", "Rossi", "01-12-1988", "PPLRSS89R03A69C");
    	Patient p = new Patient(1, "Paolo", "Rossi", "01-12-1988", "PPLRSS89R03A69C");
        given(service.insert(Mockito.any())).willReturn(p);
        mvc.perform(post("/api/patients")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(pd)))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.name", is("Paolo")));
    }
    
    @Test
    public void updateTest() throws Exception {
    	Patient p = new Patient(1, "Paolo", "Rossi", "01-12-1988", "PPLRSS89R03A69C");
        given(service.update(Mockito.any())).willReturn(p);
        mvc.perform(put("/api/patients")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(p)))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.name", is("Paolo")));
    }
    
}