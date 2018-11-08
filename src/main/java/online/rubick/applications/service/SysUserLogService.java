package online.rubick.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.sys.SysUserLogMapper;
import online.rubick.applications.entity.sys.SysUserLog;

@Service
public class SysUserLogService {

	@Autowired
	private SysUserLogMapper mapper;

	public List<SysUserLog> selectUserLogByUserId(String userId) {
		return mapper.selectUserLogByUserId(userId);
	}

	public boolean insertUserLogById(SysUserLog userLog) {
		return mapper.insert(userLog) == 1;
	}

	public Page<SysUserLog> getSysUserLogByUserId(String userId, String logUser, Pageable pageable) {
		return mapper.getSysUserLogByUserId(userId, logUser, pageable);
	}

}
