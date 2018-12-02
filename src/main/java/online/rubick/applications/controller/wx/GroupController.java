package online.rubick.applications.controller.wx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

	@GetMapping("group")
	public String hello() {
		return "Hello word!";
	}

}
