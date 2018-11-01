package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysMenu;
import online.rubick.applications.entity.sys.SysRole;

@Repository
public interface SysRoleMapper {
	int deleteByPrimaryKey(String id);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	SysRole selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);

	Page<SysRole> selectPage(@Param("status") String status, Pageable pageable);

	List<SysMenu> selectMenuByRoleId(@Param("roleId")String id);

	List<SysMenu> selectMenuByRoleIdAndParentId(@Param("roleId")String role, @Param("parentId")String parentId);

	List<SysRole> selectRoleSelect();

	List<SysRole> getAll();

	Page<SysRole> getAllPage(Pageable pageable);
	
	String findByRoleCode(@Param("roleCode")String roleCode);
}