package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysMessage;

@Repository
public interface SysMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    SysMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);

	Page<SysMessage> findMessagePage(@Param("messageType")String messageType,@Param("keyword")String keyword ,Pageable pageable);

	List<SysMessage> getAll();

}