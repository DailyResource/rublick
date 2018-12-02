package online.rubick.applications.service.rubick;

import java.util.List;

import online.rubick.applications.entity.rubick.Files;
import online.rubick.applications.service.BaseService;

public interface FilesService extends BaseService<Files> {

	List<Files> findByGroupId(String groupId);
}