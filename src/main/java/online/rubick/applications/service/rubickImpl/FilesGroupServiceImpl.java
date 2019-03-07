package online.rubick.applications.service.rubickImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.FilesGroupMapper;
import online.rubick.applications.entity.rubick.FilesGroup;
import online.rubick.applications.service.rubick.FilesGroupService;
import online.rubick.applications.util.IdUtil;

@Service
public class FilesGroupServiceImpl implements FilesGroupService {

	@Autowired
	private FilesGroupMapper mapper;

	@Override
	public int save(FilesGroup entity) {
		entity.setGroupId(IdUtil.getId());
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(FilesGroup entity) {
		entity.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public FilesGroup findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FilesGroup> getAll() {
		return mapper.getAll();
	}

	@Override
	public List<FilesGroup> findByUserIdAndType(String userId, String groupType) {
		return mapper.findByUserIdAndType(userId, groupType);
	}

}