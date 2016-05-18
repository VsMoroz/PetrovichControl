package ml.locator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.locator.model.User;

@RestController
@RequestMapping("email")
public class EmailService {
	@Autowired
	User user;
	@RequestMapping(value="sendto", method=RequestMethod.GET)
    public String sendTo(@RequestParam String email, @RequestParam String message){
		
		return "{email:" + email+ ", message:" + message + "}";
		
	}
	

}
