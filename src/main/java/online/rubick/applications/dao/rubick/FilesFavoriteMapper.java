package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.FilesFavorite;

@Repository
public interface FilesFavoriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(FilesFavorite record);

    int insertSelective(FilesFavorite record);

    FilesFavorite selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FilesFavorite record);

    int updateByPrimaryKey(FilesFavorite record);
}