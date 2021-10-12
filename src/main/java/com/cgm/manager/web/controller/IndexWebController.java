package com.cgm.manager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgm.manager.exception.CgmException;

@Controller
public class IndexWebController {
	
	@RequestMapping("/")
	public String index() throws CgmException {
		return "redirect:/patients";
	}
}
