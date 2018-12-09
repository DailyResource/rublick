package online.rubick.applications.service.rubickImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.ShopInfoMapper;
import online.rubick.applications.entity.rubick.ShopInfo;
import online.rubick.applications.service.rubick.ShopInfoService;

@Service
public class ShopInfoServiceImpl implements ShopInfoService{
 
	@Autowired
	private ShopInfoMapper mapper;

	@Override
	public int save(ShopInfo entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(ShopInfo entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public ShopInfo findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ShopInfo> getAll() {
		return null;
	}
	
}