package online.rubick.applications.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.controller.web.FileController;

@Api(description = "文件管理-微信")
@RestController
@RequestMapping("/wx/file")
@ResponseBody
public class WXFileController {

	@Autowired
	private FileController webFileController;

	@SuppressWarnings("resource")
	@ApiOperation(value = "图片展示")
	@GetMapping(value = "/getPhoto")
	public void getPhoto(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("fileCode") String fileCode) {
		webFileController.getPhoto(response, request, fileCode);
	}

	@SuppressWarnings("resource")
	@ApiOperation(value = "图片下载")
	@GetMapping(value = "/downloadPhoto")
	public void downloadPhoto(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("fileCode") String fileCode) {
		webFileController.getPhoto(response, request, fileCode);
	}

}
