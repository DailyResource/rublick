package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysUserLog;

@Repository
public interface SysUserLogMapper {

	int deleteByPrimaryKey(String logId);

	int insert(SysUserLog record);

	SysUserLog selectByPrimaryKey(String logId);

	public List<SysUserLog> selectUserLogByUserId(String userId);
	
	Page<SysUserLog> getSysUserLogByUserId(@Param("userId")String userId,@Param("logUser")String logUser, Pageable pageable);
}