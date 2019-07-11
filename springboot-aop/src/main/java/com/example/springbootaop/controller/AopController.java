package com.example.springbootaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AopController {

		@GetMapping("aop")
		public String index() {
			System.out.println("=============aop======");
			return "index";
		}
}
