package online.rubick.applications.service.rubick;

import java.util.List;

import online.rubick.applications.entity.rubick.FilesGroup;
import online.rubick.applications.service.BaseService;

public interface FilesGroupService extends BaseService<FilesGroup> {

	List<FilesGroup> findByUserIdAndType(String userId, String groupType);
}