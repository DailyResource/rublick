package online.rubick.applications.controller.wx;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.entity.rubick.Files;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.service.rubick.FilesService;

@Api(description = "文件管理")
@RestController
@RequestMapping("/wx/file")
@ResponseBody
public class WXFileController {

	@Autowired
	private FilesService filesService;

	@SuppressWarnings("resource")
	@ApiOperation(value = "图片展示")
	@GetMapping(value = "/getPhoto")
	public void getPhoto(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("fileCode") String fileCode) {
		Files file = filesService.findById(fileCode);
		if (StringUtils.isEmpty(file.getFileUrl())) {
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("没有相关的图片信息");
			} catch (IOException e) {
				throw new ApplicationException("没有相关的图片信息");
			}
		} else {
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			try {
				ServletOutputStream os = response.getOutputStream();
				FileInputStream fis = new FileInputStream(file.getFileUrl());
				os = response.getOutputStream();
				int count = 0;
				byte[] buffer = new byte[1024];
				while ((count = fis.read(buffer)) != -1) {
					os.write(buffer, 0, count);
					os.flush();
				}
			} catch (Exception e) {
				throw new ApplicationException("图片出错");
			}
		}
	}

	
	@SuppressWarnings("resource")
	@ApiOperation(value = "图片下载")
	@GetMapping(value = "/downloadPhoto")
	public void downloadPhoto(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("fileCode") String fileCode) {
		Files file = filesService.findById(fileCode);
		if (StringUtils.isEmpty(file.getFileUrl())) {
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("没有相关的图片信息");
			} catch (IOException e) {
				throw new ApplicationException("没有相关的图片信息");
			}
		} else {
			String fileName = file.getFileName()+"."+file.getExtension();
			response.setContentType("application/force-download");// 设置强制下载不打开
			try {
				// 解决文件名乱码问题
				String userAgent = request.getHeader("User-Agent");
				if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}
			} catch (UnsupportedEncodingException e1) {
				
			}
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);
			try {
				ServletOutputStream os = response.getOutputStream();
				FileInputStream fis = new FileInputStream(file.getFileUrl());
				os = response.getOutputStream();
				int count = 0;
				byte[] buffer = new byte[1024];
				while ((count = fis.read(buffer)) != -1) {
					os.write(buffer, 0, count);
					os.flush();
				}
			} catch (Exception e) {
				throw new ApplicationException("图片出错");
			}
		}
	}
}
