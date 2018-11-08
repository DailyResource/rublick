package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysMenu;
@Repository
public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    public List<SysMenu> getAll();
    
    List<String> getMenuIdsByRole(@Param("roleId")String roleId);
    
    List<SysMenu> listParentMenu();
}