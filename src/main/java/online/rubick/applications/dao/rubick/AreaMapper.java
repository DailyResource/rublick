package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.Area;

@Repository
public interface AreaMapper {
    int deleteByPrimaryKey(String codeid);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(String codeid);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}