package online.rubick.applications.controller.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import online.rubick.applications.entity.sys.SysMenu;
import online.rubick.applications.entity.sys.SysRole;
import online.rubick.applications.entity.sys.SysUserRole;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.security.annotation.SessionAccount;
import online.rubick.applications.service.SysMenuService;
import online.rubick.applications.service.SysRoleService;
import online.rubick.applications.service.SysUserRoleService;
import online.rubick.applications.util.Constant;
import online.rubick.applications.vo.sys.SysRoleVO;
import online.rubick.applications.vo.sys.UserVO;

@Api(description = "角色管理")
@RestController
@ResponseBody
@RequestMapping("/sysRole")
public class SysRoleController {
	@Autowired
	private SysMenuService menuService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysUserRoleService userRoleService;

	// 角色列表
	@ApiOperation(value = "角色列表", notes = "角色列表", response = SysRoleVO.class)
	@GetMapping("/list")
	public List<SysRoleVO> roleList() {
		List<SysRole> all = roleService.getAll();
		List<SysRoleVO> newArrayList = Lists.newArrayList();
		for (SysRole role : all) {
			SysRoleVO sysRoleVO = new SysRoleVO();
			BeanUtils.copyProperties(role, sysRoleVO);
			newArrayList.add(sysRoleVO);
		}
		return newArrayList;
	}

	// 角色列表分页
	@ApiOperation(value = "角色列表(分页)", notes = "角色列表(分页)", response = SysRoleVO.class)
	@GetMapping("/listPage")
	public Page<SysRoleVO> roleListPage(
			@ApiParam(name = "page", value = "页码") @RequestParam(value = "page", defaultValue = "0") Integer page,
			@ApiParam(name = "size", value = "条数") @RequestParam(value = "size", defaultValue = "10") Integer size) {
		Pageable pageable = new PageRequest(page, size);
		Page<SysRole> all = roleService.getAllPage(pageable);
		List<SysRoleVO> newArrayList = Lists.newArrayList();
		for (SysRole sysRole : all) {
			SysRoleVO sysRoleVO = new SysRoleVO();
			BeanUtils.copyProperties(sysRole, sysRoleVO);
			String roleId = roleService.findByRoleCode(sysRole.getRoleCode());
			if (roleId != null) {
				List<String> menus = menuService.getMenuIdsByRole(roleId);
				StringBuilder menuNames = new StringBuilder();
				if (!CollectionUtils.isEmpty(menus)) {
					for (String menu : menus) {
						SysMenu sysMenu = menuService.findById(menu);
						if (sysMenu != null && StringUtils.isNotBlank(sysMenu.getMenuName())) {
							menuNames.append(sysMenu.getMenuName() + ",");
						}
					}
				}
				sysRoleVO.setMeuns(menuNames.toString());
				newArrayList.add(sysRoleVO);
			}
		}
		return new PageImpl<>(newArrayList, pageable, all.getTotalElements());
	}

	@ApiOperation(value = "获取菜单", notes = "获取菜单")
	@GetMapping("/getMenu")
	public List<SysMenu> getMenu(@SessionAccount UserVO userVO) {
		if (userVO == null || userVO.getUserId() == null) {
			throw new ApplicationException(Constant.NOTLIGIN);
		}
		List<SysUserRole> userRoles = userRoleService.getByUserId(userVO.getUserId());
		if (!CollectionUtils.isEmpty(userRoles)) {
			for (SysUserRole userRole : userRoles) {
				String roleId = userRole.getRoleId();
				userVO.setRole(roleId);
			}
		}
		List<SysMenu> parent = menuService.listParentMenu();
		for (SysMenu menu : parent) {
			List<SysMenu> menus = roleService.selectMenuByRoleIdAndParentId(userVO.getRole(), menu.getId());
			menu.setSonMenu(menus);
			for (SysMenu firstMenu : menus) {
				List<SysMenu> secondMenus = roleService.selectMenuByRoleIdAndParentId(userVO.getRole(),
						firstMenu.getId());
				if (!secondMenus.isEmpty()) {
					firstMenu.setSonMenu(secondMenus);
				}
			}
		}
		return parent;
	}
}
