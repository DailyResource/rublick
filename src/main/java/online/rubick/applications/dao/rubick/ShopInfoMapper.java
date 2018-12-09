package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.ShopInfo;

@Repository
public interface ShopInfoMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(ShopInfo record);

    int insertSelective(ShopInfo record);

    ShopInfo selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(ShopInfo record);

    int updateByPrimaryKey(ShopInfo record);
}