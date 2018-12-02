package online.rubick.applications.service.rubickImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.UserWechatMapper;
import online.rubick.applications.entity.rubick.UserWechat;
import online.rubick.applications.service.rubick.UserWechatService;

@Service
public class UserWechatServiceImpl implements UserWechatService{
 
	@Autowired
	private UserWechatMapper mapper;

	@Override
	public int save(UserWechat entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(UserWechat entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public UserWechat findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserWechat> getAll() {
		return null;
	}
	
}