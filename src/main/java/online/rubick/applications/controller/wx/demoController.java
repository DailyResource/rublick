package online.rubick.applications.controller.wx;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class demoController {

	@GetMapping("hello")
	public String hello() {
		return "Hello word!";
	}
	
	
//	@ApiOperation(value = "图片展示", notes = "图片展示")
//	@GetMapping(value="/downloadPhoto")
//	public void downloadHeadPhoto(HttpServletResponse response, HttpServletRequest request,
//			@RequestParam("photoPath") String photoPath) throws Exception {
//
//		if (StringUtils.isEmpty(photoPath)) {
//			response.setCharacterEncoding("utf-8");
//			response.getWriter().print("没有相关的图片信息");
//		} else {
//			byte[] downloadFile = fileSvc.downloadFile(photoPath);
//			response.setHeader("Cache-Control", "no-store");
//			response.setHeader("Pragma", "no-cache");
//			response.setDateHeader("Expires", 0);
//			response.setContentType("image/jpeg");
//			ServletOutputStream responseOutputStream = response.getOutputStream();
//			responseOutputStream.write(downloadFile);
//			responseOutputStream.flush();
//			responseOutputStream.close();
//		}
//	}
	
	
	
	
}
