package online.rubick.applications.service.rubickImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.AreaMapper;
import online.rubick.applications.entity.rubick.Area;
import online.rubick.applications.service.rubick.AreaService;

@Service
public class AreaServiceImpl implements AreaService{
 
	@Autowired
	private AreaMapper mapper;

	@Override
	public int save(Area entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Area entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public Area findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Area> getAll() {
		return null;
	}
	
}