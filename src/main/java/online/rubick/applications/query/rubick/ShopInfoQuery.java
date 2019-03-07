package online.rubick.applications.query.rubick;

public class ShopInfoQuery {

	private String shopId;
	private String shopName;
	private String mobile;
	private String address;
	private String shopLogoFileCode;
	private String remark;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId == null ? null : shopId.trim();
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getShopLogoFileCode() {
		return shopLogoFileCode;
	}

	public void setShopLogoFileCode(String shopLogoFileCode) {
		this.shopLogoFileCode = shopLogoFileCode == null ? null : shopLogoFileCode.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

}