package online.rubick.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.sys.SysRoleMenuMapper;
import online.rubick.applications.entity.sys.SysRoleMenu;

@Service
public class SysRoleMenuService {
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	public List<SysRoleMenu> getAll() {
		return sysRoleMenuMapper.getAll();
	}

	public List<SysRoleMenu> getByRoleId(String roleId) {
		return sysRoleMenuMapper.getByRoleId(roleId);
	}

	public List<SysRoleMenu> getByMenuId(String menuId) {
		return sysRoleMenuMapper.getByMenuId(menuId);
	}
}
