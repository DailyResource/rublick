package online.rubick.applications.controller.wx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.controller.web.GroupController;
import online.rubick.applications.entity.rubick.FilesGroup;
import online.rubick.applications.enums.rubick.GroupTypeEnum;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.security.annotation.SessionAccount;
import online.rubick.applications.service.rubick.FilesGroupService;
import online.rubick.applications.vo.rubick.FilesGroupVO;
import online.rubick.applications.vo.rubick.FilesVO;
import online.rubick.applications.vo.sys.UserVO;

@Api(description = "分组管理-微信")
@RestController
@RequestMapping("/wx/group")
@ResponseBody
public class WXGroupController {

	@Autowired
	private GroupController webGroupController;
	@Autowired
	private FilesGroupService filesGroupService;

	@ApiOperation(value = "test")
	@GetMapping(value = "/test")
	public FilesVO test() {
		FilesVO vo = new FilesVO();
		vo.setRemark("测试Remark");
		System.out.println("测试");
		return vo;
	}

	@ApiOperation(value = "根据分组获取图片集合")
	@GetMapping(value = "/getPhotoList")
	public List<FilesVO> getPhoto(@RequestParam("groupId") String groupId) {
		return webGroupController.getPhoto(groupId);
	}

	@ApiOperation(value = "获取商品分组")
	@GetMapping("getGroup")
	public List<FilesGroupVO> getGroup(@RequestParam("userId") String userId) {
		List<FilesGroup> filesGroups = filesGroupService.findByUserIdAndType(userId, GroupTypeEnum.DIY.getCode());
		List<FilesGroupVO> list = new ArrayList<>();
		for (FilesGroup filesGroup : filesGroups) {
			FilesGroupVO vo = new FilesGroupVO();
			BeanUtils.copyProperties(filesGroup, vo);
			list.add(vo);
		}
		return list;
	}

	@ApiOperation(value = "获取下落分组")
	@GetMapping("getDropIcon")
	public FilesGroupVO getDropIcon(@RequestParam("userId") String userId) {
		List<FilesGroup> filesGroups = filesGroupService.findByUserIdAndType(userId, GroupTypeEnum.DROP.getCode());
		if (filesGroups.isEmpty()) {
			return null;
		}
		FilesGroupVO vo = new FilesGroupVO();
		BeanUtils.copyProperties(filesGroups.get(0), vo);
		return vo;
	}

}
