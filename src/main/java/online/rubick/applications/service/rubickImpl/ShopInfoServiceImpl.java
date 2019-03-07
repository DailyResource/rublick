package online.rubick.applications.service.rubickImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.rubick.applications.dao.rubick.ShopInfoMapper;
import online.rubick.applications.entity.rubick.FilesGroup;
import online.rubick.applications.entity.rubick.ShopInfo;
import online.rubick.applications.enums.rubick.GroupTypeEnum;
import online.rubick.applications.service.rubick.FilesGroupService;
import online.rubick.applications.service.rubick.ShopInfoService;
import online.rubick.applications.util.IdUtil;

@Service
public class ShopInfoServiceImpl implements ShopInfoService {

	@Autowired
	private ShopInfoMapper mapper;

	@Autowired
	private FilesGroupService filesGroupService;

	@Override
	public int save(ShopInfo entity) {
		entity.setShopId(IdUtil.getId());
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		return mapper.insert(entity);
	}

	@Override
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(ShopInfo entity) {
		entity.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public ShopInfo findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ShopInfo> getAll() {
		return mapper.getAll();
	}

	@Override
	public void createShopInfo(String userId) {
		ShopInfo shopInfo = new ShopInfo();
		shopInfo.setUserId(userId);
		this.save(shopInfo);

		FilesGroup groupDrop = new FilesGroup();
		groupDrop.setGroupName("下落图标");
		groupDrop.setGroupType(GroupTypeEnum.DROP.getCode());
		groupDrop.setUserId(userId);
		groupDrop.setRemark("系统默认分组");
		filesGroupService.save(groupDrop);

		FilesGroup groupNoFroup = new FilesGroup();
		groupNoFroup.setGroupName("未分组");
		groupNoFroup.setGroupType(GroupTypeEnum.NO_GROUP.getCode());
		groupNoFroup.setUserId(userId);
		groupNoFroup.setRemark("暂未分组图片，不显示在小程序页面上");
		filesGroupService.save(groupNoFroup);

	}

}