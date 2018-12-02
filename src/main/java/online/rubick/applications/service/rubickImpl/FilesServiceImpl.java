package online.rubick.applications.service.rubickImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.FilesMapper;
import online.rubick.applications.entity.rubick.Files;
import online.rubick.applications.service.rubick.FilesService;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesMapper mapper;

	@Override
	public int save(Files entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Files entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public Files findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Files> getAll() {
		return null;
	}

	@Override
	public List<Files> findByGroupId(String groupId) {
		return mapper.selectByGroupId(groupId);
	}

}