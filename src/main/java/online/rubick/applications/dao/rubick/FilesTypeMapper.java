package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.FilesType;

@Repository
public interface FilesTypeMapper {
    int deleteByPrimaryKey(String fileTypeCode);

    int insert(FilesType record);

    int insertSelective(FilesType record);

    FilesType selectByPrimaryKey(String fileTypeCode);

    int updateByPrimaryKeySelective(FilesType record);

    int updateByPrimaryKey(FilesType record);
}