package online.rubick.applications.dao.sys;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysOptionLog;

@Repository
public interface SysOptionLogMapper {
	int deleteByPrimaryKey(String id);

	int insert(SysOptionLog record);

	int insertSelective(SysOptionLog record);

	SysOptionLog selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysOptionLog record);

	int updateByPrimaryKey(SysOptionLog record);

	Page<SysOptionLog> selectPage(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("projectName")String projectName, Pageable pageable);
}