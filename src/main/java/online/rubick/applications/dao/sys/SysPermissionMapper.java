package online.rubick.applications.dao.sys;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysPermission;
@Repository
public interface SysPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}