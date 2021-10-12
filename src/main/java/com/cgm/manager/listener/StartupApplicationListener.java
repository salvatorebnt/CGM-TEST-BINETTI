package com.cgm.manager.listener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cgm.manager.model.patient.PatientData;
import com.cgm.manager.model.visit.VisitData;
import com.cgm.manager.service.PatientsService;
import com.cgm.manager.service.VisitsService;

/**
 * Implementation of ApplicationListener.
 * In this project it is needed just to initialize our h2 db with some mock data
 * @author salvatore.binetti
 *
 */
@Component
@Profile("!test")
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
	static Logger log = LoggerFactory.getLogger(StartupApplicationListener.class);
	/**
	 * Service class serving patient services
	 */
	@Autowired
	protected PatientsService patientsService;
	
	/**
	 * Visit class serving visit services
	 */
	@Autowired
	protected VisitsService visitsService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			long patientsCount = patientsService.count();
			long visitsCount = visitsService.count();
			if(patientsCount == 0 && visitsCount == 0) {
				patientsService.saveAll(getTestPatients());
				visitsService.saveAll(getTestVisits());
			}
		} catch (Exception e) {
			log.error("Error during StartupApplicationListener");
			log.error(e.getMessage());
		}
	}

	private List<PatientData> getTestPatients() {
		List<PatientData> list = new ArrayList<>();
		list.add(new PatientData("Vasco", "Rossi", "01-12-1988", "VSCRSS6303A776E"));
		list.add(new PatientData("Axl", "Roses", "12-12-1960", "RSSXLL5512A871A"));
		list.add(new PatientData("Brian", "Johnson", "04-01-1953", "JNSBRN61R02A769B"));
		return list;
	}
	

	private List<VisitData> getTestVisits() {
		List<VisitData> list = new ArrayList<>();
		list.add(new VisitData(1, "10-09-2020T10:00:00Z", "Home", "Recurring visit", ""));
		list.add(new VisitData(2, "10-09-2020T12:00:00Z", "Home", "Urgent", "Small notes"));
		list.add(new VisitData(3, "11-09-2020T09:00:00Z", "Office", "First visit", "Notes Notes Notes Notes Notes Notes Notes Notes Notes "));
		return list;
	}

}
