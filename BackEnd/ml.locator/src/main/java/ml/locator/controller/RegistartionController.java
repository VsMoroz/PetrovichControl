package ml.locator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistartionController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String register(@RequestParam String name){
		return "test";
	}

}
