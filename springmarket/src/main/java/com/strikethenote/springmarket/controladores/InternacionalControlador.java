package com.strikethenote.springmarket.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternacionalControlador {
	
	@GetMapping("/international")
	public String getInternationalPage() {
		return "international";
	}

}
