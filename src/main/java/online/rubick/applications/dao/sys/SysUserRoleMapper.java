package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysUserRole;
@Repository
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
    
    List<SysUserRole> getByUserId(@Param("userId")String userId);

	int delectRoleUserRelationByUserId(@Param("userId")String userId);

	int updateByUserId(SysUserRole sysUserRole);
}