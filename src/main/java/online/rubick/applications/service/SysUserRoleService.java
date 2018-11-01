package online.rubick.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.sys.SysUserRoleMapper;
import online.rubick.applications.entity.sys.SysUserRole;

@Service
public class SysUserRoleService {

    @Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	public int save(SysUserRole entity) {
			return sysUserRoleMapper.insert(entity);
	}

	public int delete(String id) {
		return sysUserRoleMapper.deleteByPrimaryKey(id);
	}

	public int update(SysUserRole entity) {
		return sysUserRoleMapper.updateByPrimaryKeySelective(entity);
	}

	public SysUserRole findById(String id) {
		return sysUserRoleMapper.selectByPrimaryKey(id);
	}

	public List<SysUserRole> getAll() {
		return null;
	}

	public List<SysUserRole> getByUserId(String userId) {
		return sysUserRoleMapper.getByUserId(userId);
	}

	public int updateByUserId(SysUserRole sysUserRole) {
		return sysUserRoleMapper.updateByUserId(sysUserRole);
	}

	
	
}
