package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysRolePermission;
@Repository
public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
    
    List<SysRolePermission> getByRoleId(@Param("roleId")String roleId);
}