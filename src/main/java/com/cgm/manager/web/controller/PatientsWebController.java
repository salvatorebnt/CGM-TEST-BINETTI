package com.cgm.manager.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.service.PatientsService;

@Controller
@RequestMapping(value = "/patients")
public class PatientsWebController {
	Logger logger = LoggerFactory.getLogger(PatientsWebController.class);

	@Autowired
	PatientsService patientService;

	@RequestMapping(method = RequestMethod.GET)
	public String findAll(Model model) throws CgmException {
		List<Patient> list = patientService.findAll();
		model.addAttribute("patients", list);
		return "patients";
	}
	
}