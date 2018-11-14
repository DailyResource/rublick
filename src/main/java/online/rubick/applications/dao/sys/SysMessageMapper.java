package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import online.rubick.applications.Query.MessageManagementQuery;
import online.rubick.applications.entity.sys.SysMessage;
import online.rubick.applications.vo.sys.MessageVo;

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

	Page<MessageVo> selectAllMessage(@Param("query")MessageManagementQuery messageQuery,Pageable pageable);
	
	/**
	 * 将消息未读标记为已读并记录操作人和操作时间
	 * @param readFlag
	 * @param Id
	 * @return
	 */
    int updateReadFlagById(@Param("readFlag")String readFlag,@Param("optionUser")String optionUser,@Param("optiontime")String optiontime,@Param("Id")String Id);	
  
    /**
	 * 根据条件进行搜索查询所有的未读信息
	 * @param messagemanageVo
	 * @return
	 */
	List<String> selectAllMessageByAllNORead(@Param("query")MessageManagementQuery messageQuery);
	
}