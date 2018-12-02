package online.rubick.applications.controller.wx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import online.rubick.applications.entity.rubick.FilesGroup;
import online.rubick.applications.service.rubick.FilesGroupService;
import online.rubick.applications.vo.rubick.FilesGroupVO;

@RestController
public class GroupController {
	@Autowired
	private FilesGroupService filesGroupService;

	@GetMapping("getGroup")
	public List<FilesGroupVO> getGroup() {
		List<FilesGroup> filesGroups = filesGroupService.getAll();
		List<FilesGroupVO> list =new ArrayList<>();
		for (FilesGroup filesGroup : filesGroups) {
			FilesGroupVO vo =new FilesGroupVO();
			BeanUtils.copyProperties(filesGroup, vo);
			list.add(vo);
		}
		return list;
	}

}
