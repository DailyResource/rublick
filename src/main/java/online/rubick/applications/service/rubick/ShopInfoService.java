package online.rubick.applications.service.rubick;

import online.rubick.applications.entity.rubick.ShopInfo;
import online.rubick.applications.service.BaseService;

public interface ShopInfoService extends BaseService<ShopInfo> {

	void createShopInfo(String userId);
}