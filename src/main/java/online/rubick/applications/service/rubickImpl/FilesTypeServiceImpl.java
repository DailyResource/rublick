package online.rubick.applications.service.rubickImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.FilesTypeMapper;
import online.rubick.applications.entity.rubick.FilesType;
import online.rubick.applications.service.rubick.FilesTypeService;

@Service
public class FilesTypeServiceImpl implements FilesTypeService{
 
	@Autowired
	private FilesTypeMapper mapper;

	@Override
	public int save(FilesType entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(FilesType entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public FilesType findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FilesType> getAll() {
		return null;
	}
	
}