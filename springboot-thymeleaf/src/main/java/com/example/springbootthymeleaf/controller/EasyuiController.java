package com.example.springbootthymeleaf.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EasyuiController {

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ModelAndView greeting(@RequestParam(name = "name", required = false, defaultValue = "world") String name, Model model) {
		model.addAttribute("xname", name);
		return new ModelAndView("index", "helloModel", model);
	}

	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public String ajax(@RequestParam("username") String username) {
		return username;
	}
}
