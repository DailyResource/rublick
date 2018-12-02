package online.rubick.applications.controller.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import online.rubick.applications.entity.rubick.FilesGroup;
import online.rubick.applications.service.rubick.FilesGroupService;

@RestController
public class GroupController {
	@Autowired
	private FilesGroupService filesGroupService;

	@GetMapping("getGroup")
	public String getGroup() {
		List<FilesGroup> filesGroups = filesGroupService.getAll();
		
		
		return "Hello word!";
	}

}
