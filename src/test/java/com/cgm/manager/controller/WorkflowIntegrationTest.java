package com.cgm.manager.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgm.manager.config.AppConfig;
import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.model.patient.PatientData;
import com.cgm.manager.model.visit.Visit;
import com.cgm.manager.model.visit.VisitData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AppConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ActiveProfiles("test")
public class WorkflowIntegrationTest {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	static HttpHeaders headers = new HttpHeaders();
	
    @BeforeClass
    public static void setup() {
    	headers.setContentType(MediaType.APPLICATION_JSON);
    }
    
    @Test
    public void workflow() {
    	//find all visits... it shound return no results
    	List<Visit> visits = Arrays.asList(findAllVisits());
    	assert(visits.isEmpty());
    	
    	//find all patients... it shound return no results
    	List<Patient> patients = Arrays.asList(findAllPatients());
    	assert(patients.isEmpty());
    	
    	//save a patient and verify id = 1
    	Patient patient = savePatient();
    	assert(patient.getId() == 1);
    	
    	//save a visit for the previous patient and verify id = 1 and idParent = 1
    	Visit visit = saveVisit(patient.getId());
    	assert(visit.getId() == 1);
    	assert(visit.getIdPatient() == patient.getId());
    	
    	//find all visits by the same patient... it shound return 1 result
    	List<Visit> visitsByPatient = Arrays.asList(findVisitsByPatient(patient.getId()));
    	assert(visitsByPatient.size() == 1);
    	Visit visitFound = visitsByPatient.get(0);
    	
    	//update the visit - find a visit by identification and verify the changes
    	Visit updatedVisit = updateVisit(visitFound);
    	Visit visitFound2 = findVisitsById(updatedVisit.getId());
    	
    	assert(visitFound.getVisitType().equals("Home") && visitFound2.getVisitType().equals("Office"));
    }
    
	private Visit[] findAllVisits() {
		return restTemplate.getForObject(createURLWithPort("/management/api/visits"), 
				Visit[].class); 
	}

	private Patient[] findAllPatients() {
		return restTemplate.getForObject(createURLWithPort("/management/api/patients"), 
				Patient[].class);   	
	}
	
	private Patient savePatient() {
    	PatientData pd = getMockPatientData();
		HttpEntity<PatientData> entity = new HttpEntity<PatientData>(pd, headers);
		return restTemplate.postForObject(
				createURLWithPort("/management/api/patients"),
				entity, 
				Patient.class); 	
	}
	
	private Visit saveVisit(long id) {
    	VisitData vd = getMockVisitData(id);
		HttpEntity<VisitData> entity = new HttpEntity<VisitData>(vd, headers);
		return restTemplate.postForObject(
				createURLWithPort("/management/api/visits"),
				entity, 
				Visit.class); 
	}

	private Visit[] findVisitsByPatient(long id) {
		return restTemplate.getForObject(createURLWithPort("/management/api/visits/patient/" + id), 
				Visit[].class);
	}
	
	private Visit updateVisit(Visit visit) {
    	Visit newVisit = new Visit(visit.getId(), visit.getIdPatient(), visit.getVisitDate(), "Office", visit.getVisitReason(), visit.getNotes());
		HttpEntity<Visit> entity = new HttpEntity<Visit>(newVisit, headers);
		return restTemplate.exchange(createURLWithPort("/management/api/visits"), HttpMethod.PUT, entity, Visit.class).getBody();
	}
	
	private Visit findVisitsById(long id) {
		return restTemplate.getForObject(createURLWithPort("/management/api/visits/" + id), 
				Visit.class);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
    
    private PatientData getMockPatientData() {
    	return new PatientData("Paolo", "Rossi", "01-12-1988", "PPLRSS89R03A69C");
    }
    
    private VisitData getMockVisitData(long idParent) {
    	return new VisitData(idParent, "10-09-2020T10:00:00Z", "Home", "Urgent", "");
    }
}

	
