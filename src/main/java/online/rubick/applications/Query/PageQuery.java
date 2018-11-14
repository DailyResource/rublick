package online.rubick.applications.Query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询条件
 * 
 * @author 林鹏
 *
 */
@ApiModel(value = "PageQuery", description = "分页查询信息")
public class PageQuery {
	
	@ApiModelProperty(value = "页码,默认0")
	private int page;

	@ApiModelProperty(value = "页长，默认10")
	private int size;

	public int getPage() {
		if (page < 0) {
			return 0;
		} else {
			return page;
		}
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size == 0 ? 10 : size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
