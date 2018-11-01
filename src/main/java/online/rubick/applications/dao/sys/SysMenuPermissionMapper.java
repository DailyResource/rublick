package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysMenuPermission;
@Repository
public interface SysMenuPermissionMapper {
    int insert(SysMenuPermission record);

    int insertSelective(SysMenuPermission record);
    
    List<SysMenuPermission> getAll();
    
    List<SysMenuPermission> getByMenuId(@Param("menuId")String menuId);
}