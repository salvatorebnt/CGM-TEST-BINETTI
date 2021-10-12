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

import com.cgm.manager.model.visit.Visit;
import com.cgm.manager.model.visit.VisitData;
import com.cgm.manager.service.VisitsService;
import com.cgm.manager.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(VisitsController.class)
public class VisitsControllerMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VisitsService service;

    @Test
    public void findAllTest()
      throws Exception {
        Visit v = getMockVisit();
        List<Visit> allVisits = Arrays.asList(v);
        given(service.findAll()).willReturn(allVisits);

        mvc.perform(get("/api/visits")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].id", is((int)v.getId())));
    }
    
    @Test
    public void findByIdTest()
      throws Exception {
    	long id = 1;
        Visit v = getMockVisit();
        given(service.findById(id)).willReturn(v);
        mvc.perform(get("/api/visits/" + id)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.idPatient", is((int)v.getIdPatient())));
    }
    
    @Test
    public void insertTest() throws Exception {
    	VisitData vd = getMockVisitData();
    	Visit v = getMockVisit();
        given(service.insert(Mockito.any())).willReturn(v);
        mvc.perform(post("/api/visits")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(vd)))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.idPatient", is((int)v.getIdPatient())));
    }
    
    @Test
    public void updateTest() throws Exception {
    	Visit v = getMockVisit();
        given(service.update(Mockito.any())).willReturn(v);
        mvc.perform(put("/api/visits")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(v)))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.idPatient", is((int)v.getIdPatient())));
    }
    
    private Visit getMockVisit() {
    	return new Visit(1, 1, "10-09-2020T10:00:00Z", "Home", "Urgent", "");
    }
    
    private VisitData getMockVisitData() {
    	return new VisitData(1, "10-09-2020T10:00:00Z", "Home", "Urgent", "");
    }
}