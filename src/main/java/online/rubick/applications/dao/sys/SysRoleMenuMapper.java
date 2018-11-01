package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysRoleMenu;
@Repository
public interface SysRoleMenuMapper {
    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);
    
    List<SysRoleMenu> getAll();
    
    List<SysRoleMenu> getByRoleId(@Param("roleId")String roleId);
    
    List<SysRoleMenu> getByMenuId(@Param("menuId")String menuId);
}