package online.rubick.applications.controller.wx;

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
import online.rubick.applications.enums.rubick.FileStatusEnum;
import online.rubick.applications.enums.rubick.GroupTypeEnum;
import online.rubick.applications.security.annotation.SessionAccount;
import online.rubick.applications.service.rubick.FilesGroupService;
import online.rubick.applications.service.rubick.FilesService;
import online.rubick.applications.vo.rubick.FilesGroupVO;
import online.rubick.applications.vo.rubick.FilesVO;
import online.rubick.applications.vo.sys.UserVO;

@Api(description = "分组管理")
@RestController
@RequestMapping("/wx/group")
@ResponseBody
public class WXGroupController {
	@Autowired
	private FilesGroupService filesGroupService;
	@Autowired
	private FilesService filesService;

	@ApiOperation(value = "根据分组获取图片集合")
	@GetMapping(value = "/getPhotoList")
	public List<FilesVO> getPhoto(@RequestParam("groupId") String groupId) {
		List<Files> filesList = filesService.findByGroupId(groupId);
		List<FilesVO> list = new ArrayList<>();
		for (Files files : filesList) {
			FilesVO vo = new FilesVO();
			BeanUtils.copyProperties(files, vo);
			vo.setStatusName(FileStatusEnum.getEnumByCode(files.getStatus()).getDescription());
			list.add(vo);
		}
		return list;
	}

	@ApiOperation(value = "获取分组")
	@GetMapping("getGroup")
	public List<FilesGroupVO> getGroup(@SessionAccount UserVO userVO) {
		List<FilesGroup> filesGroups = filesGroupService.findByUserIdAndType(userVO.getUserId(), null);
		List<FilesGroupVO> list = new ArrayList<>();
		for (FilesGroup filesGroup : filesGroups) {
			FilesGroupVO vo = new FilesGroupVO();
			BeanUtils.copyProperties(filesGroup, vo);
			list.add(vo);
		}
		return list;
	}

	@ApiOperation(value = "获取下落图标")
	@GetMapping("getDropIcon")
	public FilesGroupVO getDropIcon(@SessionAccount UserVO userVO) {
		List<FilesGroup> filesGroups = filesGroupService.findByUserIdAndType(userVO.getUserId(),
				GroupTypeEnum.DROP.getCode());
		if (filesGroups.isEmpty()) {
			return null;
		}
		FilesGroupVO vo = new FilesGroupVO();
		BeanUtils.copyProperties(filesGroups.get(0), vo);
		return vo;
	}

}
