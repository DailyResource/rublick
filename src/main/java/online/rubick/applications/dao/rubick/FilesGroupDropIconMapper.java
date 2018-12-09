package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.FilesGroupDropIcon;

@Repository
public interface FilesGroupDropIconMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(FilesGroupDropIcon record);

    int insertSelective(FilesGroupDropIcon record);

    FilesGroupDropIcon selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(FilesGroupDropIcon record);

    int updateByPrimaryKey(FilesGroupDropIcon record);
}