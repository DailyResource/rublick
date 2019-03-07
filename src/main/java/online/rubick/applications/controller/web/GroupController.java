package online.rubick.applications.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import online.rubick.applications.query.rubick.FilesGroupQuery;
import online.rubick.applications.security.annotation.SessionAccount;
import online.rubick.applications.service.rubick.FilesGroupService;
import online.rubick.applications.service.rubick.FilesService;
import online.rubick.applications.vo.rubick.FilesGroupVO;
import online.rubick.applications.vo.rubick.FilesVO;
import online.rubick.applications.vo.sys.UserVO;

@Api(description = "分组管理")
@RestController
@RequestMapping("/web/group")
@ResponseBody
public class GroupController {

	@Autowired
	private FilesGroupService filesGroupService;

	@Autowired
	private FilesService filesService;

	@ApiOperation(value = "获取所有商品分组")
	@GetMapping("getGroupUse")
	public List<FilesGroupVO> getGroupUse(@SessionAccount UserVO userVO) {
		List<FilesGroup> filesGroups = filesGroupService.findByUserIdAndType(userVO.getUserId(),
				GroupTypeEnum.DIY.getCode());
		List<FilesGroupVO> list = new ArrayList<>();
		for (FilesGroup filesGroup : filesGroups) {
			FilesGroupVO vo = new FilesGroupVO();
			BeanUtils.copyProperties(filesGroup, vo);
			list.add(vo);
		}
		return list;
	}

	@ApiOperation(value = "获取所有分组")
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

	@ApiOperation(value = "创建新分组")
	@PostMapping("createGroup")
	public void createGroup(@RequestBody FilesGroupQuery groupQuery, @SessionAccount UserVO userVO) {
		FilesGroup group = new FilesGroup();
		BeanUtils.copyProperties(groupQuery, group);
		group.setUserId(userVO.getUserId());
		group.setGroupType(GroupTypeEnum.DIY.getCode());
		filesGroupService.save(group);
	}

	@ApiOperation(value = "	修改分组")
	@PostMapping("updateGroup")
	public void updateGroup(@RequestBody FilesGroupQuery groupQuery) {
		FilesGroup group = filesGroupService.findById(groupQuery.getGroupId());
		group.setGroupName(groupQuery.getGroupName());
		group.setRemark(groupQuery.getRemark());
		filesGroupService.update(group);
	}

	@ApiOperation(value = "	删除分组")
	@DeleteMapping("deleteGroup")
	public void deleteGroup(@RequestParam("groupId") String groupId, @SessionAccount UserVO userVO) {
		filesGroupService.delete(groupId);
		String newGroupId = filesGroupService.findByUserIdAndType(userVO.getUserId(), GroupTypeEnum.NO_GROUP.getCode())
				.get(0).getGroupId();
		filesService.updateFilesGroupId(groupId, newGroupId);
	}

}
