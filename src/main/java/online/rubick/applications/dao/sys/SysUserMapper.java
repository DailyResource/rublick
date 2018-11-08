package online.rubick.applications.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import online.rubick.applications.entity.sys.SysUser;

@Repository
public interface SysUserMapper {
	int deleteByPrimaryKey(String userId);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(String userId);

	List<SysUser> selectByLoginId(String loginId);

	int updateByPrimaryKeySelective(SysUser record);

	Page<SysUser> getUsersByIds(@Param("ids") List<String> ids, Pageable pageable);

	List<String> selectRoleByUserId(@Param("userId") String userId, @Param("roleId") String roleId);

	void updatePasswordByLoginId(@Param("password") String password, @Param("loginId") String loginId);

	List<SysUser> selectAll();

	List<SysUser> getUserByMoble(@Param("mobile") String mobile);

	List<SysUser> getFixUserByMoble(@Param("mobile") String mobile);

	void updateClientId(SysUser user);

	int deleteUserById(String userId);

	Page<SysUser> getAllPage(@Param("keyword") String keyword, Pageable pageable);

	SysUser findByUserId(@Param("userId") String userId);

	SysUser findByLoginId(@Param("loginId") String loginId);

	List<SysUser> findByMobile(@Param("mobile") String mobile);
}