package online.rubick.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.sys.SysMenuMapper;
import online.rubick.applications.entity.sys.SysMenu;

@Service
public class SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;

	public SysMenu findById(String id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	public List<SysMenu> getAll() {
		return sysMenuMapper.getAll();
	}

	public List<String> getMenuIdsByRole(String roleId) {
		return sysMenuMapper.getMenuIdsByRole(roleId);
	}

	public List<SysMenu> listParentMenu() {
		return sysMenuMapper.listParentMenu();
	}
}
