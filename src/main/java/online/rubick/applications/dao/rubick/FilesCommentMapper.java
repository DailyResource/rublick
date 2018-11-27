package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.FilesComment;

@Repository
public interface FilesCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(FilesComment record);

    int insertSelective(FilesComment record);

    FilesComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FilesComment record);

    int updateByPrimaryKey(FilesComment record);
}