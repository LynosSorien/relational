package com.djorquab.relational.relational.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class PagesController {
	
	@GetMapping
	public ModelAndView home() {
		return new ModelAndView("index");
	}
}
