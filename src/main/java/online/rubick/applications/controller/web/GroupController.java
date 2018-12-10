package online.rubick.applications.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.entity.rubick.Files;
import online.rubick.applications.entity.rubick.FilesGroup;
import online.rubick.applications.entity.rubick.FilesGroupDropIcon;
import online.rubick.applications.enums.rubick.FileStatus;
import online.rubick.applications.service.rubick.FilesGroupDropIconService;
import online.rubick.applications.service.rubick.FilesGroupService;
import online.rubick.applications.service.rubick.FilesService;
import online.rubick.applications.vo.rubick.FilesGroupDropIconVO;
import online.rubick.applications.vo.rubick.FilesGroupVO;
import online.rubick.applications.vo.rubick.FilesVO;

@Api(description = "分组管理")
@RestController
@RequestMapping("/web/group")
@ResponseBody
public class GroupController {
	@Autowired
	private FilesGroupService filesGroupService;
	@Autowired
	private FilesGroupDropIconService filesGroupDropIconService;
	@Autowired
	private FilesService filesService;
	
	@ApiOperation(value = "获取下落图标分组")
	@GetMapping("getDropGroup")
	public List<FilesGroupDropIconVO> getDropGroup() {
		List<FilesGroupDropIcon> filesGroups = filesGroupDropIconService.getAll();
		List<FilesGroupDropIconVO> list = new ArrayList<>();
		for (FilesGroupDropIcon filesGroup : filesGroups) {
			FilesGroupDropIconVO vo = new FilesGroupDropIconVO();
			BeanUtils.copyProperties(filesGroup, vo);
			list.add(vo);
		}
		return list;
	}

}
