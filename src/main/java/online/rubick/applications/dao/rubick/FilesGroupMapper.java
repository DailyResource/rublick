package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.FilesGroup;

@Repository
public interface FilesGroupMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(FilesGroup record);

    int insertSelective(FilesGroup record);

    FilesGroup selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(FilesGroup record);

    int updateByPrimaryKey(FilesGroup record);
}