package online.rubick.applications.controller.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.entity.rubick.ShopInfo;
import online.rubick.applications.query.rubick.ShopInfoQuery;
import online.rubick.applications.service.rubick.ShopInfoService;
import online.rubick.applications.vo.rubick.ShopInfoVO;

@Api(description = "分组管理")
@RestController
@RequestMapping("/web/shop")
@ResponseBody
public class ShopController {

	@Autowired
	private ShopInfoService shopInoService;;

	@ApiOperation(value = "获取商户信息")
	@GetMapping(value = "/getPhotoList")
	public ShopInfoVO getPhoto(@RequestParam("shopId") String shopId) {
		ShopInfo shopInfo = shopInoService.findById(shopId);
		ShopInfoVO vo = new ShopInfoVO();
		BeanUtils.copyProperties(shopInfo, vo);
		return vo;
	}

	@ApiOperation(value = "修改商户信息")
	@GetMapping("getDropIcon")
	public void getDropIcon(@RequestBody ShopInfoQuery shopQuery) {
		ShopInfo shopInfo = shopInoService.findById(shopQuery.getShopId());
		shopInfo.setAddress(shopQuery.getAddress());
		shopInfo.setMobile(shopQuery.getMobile());
		shopInfo.setShopName(shopQuery.getShopName());
		shopInfo.setShopLogoFileCode(shopQuery.getShopLogoFileCode());
		shopInfo.setRemark(shopQuery.getRemark());
		shopInoService.update(shopInfo);
	}

}
