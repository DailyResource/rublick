package online.rubick.applications.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.sys.SysRoleMapper;
import online.rubick.applications.entity.sys.SysMenu;
import online.rubick.applications.entity.sys.SysRole;
import online.rubick.applications.util.IdUtil;

@Service
public class SysRoleService {
    
	@Autowired
	private SysRoleMapper sysRoleMapper;

	public int save(SysRole entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(IdUtil.getId() + "");
		}
		return sysRoleMapper.insertSelective(entity);
	}

	public int delete(String id) {
		return sysRoleMapper.deleteByPrimaryKey(id);
	}

	public int update(SysRole entity) {
		return sysRoleMapper.updateByPrimaryKeySelective(entity);
	}

	public SysRole findById(String id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	public List<SysRole> getAll() {
		return sysRoleMapper.getAll();
	}

	public Page<SysRole> selectPage(Pageable pageable, String status) {
		return sysRoleMapper.selectPage(status, pageable);
	}

	public List<SysMenu> selectMenuByRoleId(String id) {
		return sysRoleMapper.selectMenuByRoleId(id);
	}

	public List<SysMenu> selectMenuByRoleIdAndParentId(String role, String parentId) {
		return sysRoleMapper.selectMenuByRoleIdAndParentId(role, parentId);
	}

	public List<SysRole> selectRoleSelect(@NotBlank(message = "role isNull") String role) {
		List<SysRole> roleList = sysRoleMapper.selectRoleSelect();
		if (role.equals("1")) {
			return roleList;
		}
		if (role.equals("5")) {
			return roleList.stream().filter(roleVo -> {
				return roleVo.getId().equals("6") || roleVo.getId().equals("7");
			}).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public Page<SysRole> getAllPage(Pageable pageable) {
		return sysRoleMapper.getAllPage(pageable);
	}

	public String findByRoleCode(String roleCode) {
		return sysRoleMapper.findByRoleCode(roleCode);
	}
}
