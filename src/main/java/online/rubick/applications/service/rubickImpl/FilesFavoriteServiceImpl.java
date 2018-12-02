package online.rubick.applications.service.rubickImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.FilesFavoriteMapper;
import online.rubick.applications.entity.rubick.FilesFavorite;
import online.rubick.applications.service.rubick.FilesFavoriteService;

@Service
public class FilesFavoriteServiceImpl implements FilesFavoriteService{
 
	@Autowired
	private FilesFavoriteMapper mapper;

	@Override
	public int save(FilesFavorite entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(FilesFavorite entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public FilesFavorite findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FilesFavorite> getAll() {
		return null;
	}
	
}