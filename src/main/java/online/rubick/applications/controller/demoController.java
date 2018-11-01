package online.rubick.applications.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

	@GetMapping("hello")
	public String hello() {
		return "Hello word!";
	}
}
