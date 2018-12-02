package online.rubick.applications.dao.rubick;

import java.util.List;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.Files;

@Repository
public interface FilesMapper {
    int deleteByPrimaryKey(String fileCode);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(String fileCode);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);
    
   List<Files> selectByGroupId(String groupId);
}