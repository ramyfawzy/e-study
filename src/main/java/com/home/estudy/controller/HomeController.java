package com.home.estudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;

@Controller
public class HomeController {

	@Operation(hidden = true)
	@RequestMapping("/")
	public String home() {
		return "redirect:/swagger-ui/index.html?url=/v3/api-docs&operationsSorter=method&displayOperationId=true";
	}
}
