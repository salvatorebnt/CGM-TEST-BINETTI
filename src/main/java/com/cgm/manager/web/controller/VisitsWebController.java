package com.cgm.manager.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgm.manager.constants.VisitsRestConstants;
import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.model.visit.Visit;
import com.cgm.manager.service.PatientsService;
import com.cgm.manager.service.VisitsService;

@Controller
@RequestMapping(value = "/visits")
public class VisitsWebController {
	Logger logger = LoggerFactory.getLogger(VisitsWebController.class);

	@Autowired
	VisitsService visitService;
	
	@Autowired
	PatientsService patientService;

	@RequestMapping(value="/patient/{id}", method = RequestMethod.GET)
	public String findAll(@PathVariable(name = VisitsRestConstants.ID_PARAM, required = true) Long id, Model model) throws CgmException {
		List<Visit> list = visitService.findByIdPatient(id);
		Patient patient = patientService.findById(id);
		model.addAttribute("visits", list);
		model.addAttribute("patient", patient);
		return "visits";
	}
	
}