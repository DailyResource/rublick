package online.rubick.applications.dao.sys;

import java.util.Date;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysCatalog;
@Repository
public interface SysCatalogMapper {
    int deleteByPrimaryKey(Date id);

    int insert(SysCatalog record);

    int insertSelective(SysCatalog record);

    SysCatalog selectByPrimaryKey(Date id);

    int updateByPrimaryKeySelective(SysCatalog record);

    int updateByPrimaryKey(SysCatalog record);
}