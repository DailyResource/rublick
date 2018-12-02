package online.rubick.applications.service.rubickImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.FilesCommentMapper;
import online.rubick.applications.entity.rubick.FilesComment;
import online.rubick.applications.service.rubick.FilesCommentService;

@Service
public class FilesCommentServiceImpl implements FilesCommentService{
 
	@Autowired
	private FilesCommentMapper mapper;

	@Override
	public int save(FilesComment entity) {
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(FilesComment entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public FilesComment findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FilesComment> getAll() {
		return null;
	}
	
}