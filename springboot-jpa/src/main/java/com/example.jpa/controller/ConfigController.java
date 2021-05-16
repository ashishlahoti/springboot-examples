package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.config.PersonConfig;

@RestController
@RequestMapping("/config")
public class ConfigController {

	/* Define a set of properties in config class using @ConfigurationProperties */
	@Autowired
	private PersonConfig personConfig;

	/* Assign default value of property if value is not defined */
	@Value("${person.flaws : No Flaws}")
	private String flaws;
	

	/* Assign inline value of property */
	@Value("This is inline property")
	private String inlineProperty;

	@GetMapping("/person")
	private PersonConfig getPersonConfig() {
		System.out.println(personConfig);
		return personConfig;
	}

	@GetMapping("/person/flaws")
	private String getPersonFlaws() {
		return flaws;
	}

	@GetMapping("/inline")
	private String getInlineProperty() {
		return inlineProperty;
	}
}
