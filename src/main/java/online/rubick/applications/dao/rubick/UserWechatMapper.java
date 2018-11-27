package online.rubick.applications.dao.rubick;

import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.rubick.UserWechat;

@Repository
public interface UserWechatMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserWechat record);

    int insertSelective(UserWechat record);

    UserWechat selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserWechat record);

    int updateByPrimaryKey(UserWechat record);
}