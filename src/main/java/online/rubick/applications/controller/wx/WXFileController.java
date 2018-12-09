package online.rubick.applications.controller.wx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import online.rubick.applications.entity.rubick.Files;
import online.rubick.applications.enums.rubick.FileStatus;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.service.rubick.FilesService;
import online.rubick.applications.util.IdUtil;

@Api(description = "文件管理")
@RestController
@RequestMapping("/wx/file")
@ResponseBody
public class WXFileController {

	@Value("${file.prefix}")
	private String prefix;

	@Value("${file.prefixSecondSmall}")
	private String prefixSecondSmall;

	@Value("${file.prefixSecondBig}")
	private String prefixSecondBig;

	@Autowired
	private FilesService filesService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "请求成功") })
	@ApiOperation(value = "上传文件", notes = "上传成功后，服务器将返回文件的地址信息。")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public void uploadFile(@ApiParam(name = "file", value = "文件") MultipartFile file) throws Exception {
		// 1.判断获取的文件信息是否为空
		if (StringUtils.isEmpty(file)) {
			throw new ApplicationContextException("读取文件发生错误");
		}
		Files fileInsert = new Files();
		fileInsert.setFileCode(IdUtil.getId());
		fileInsert.setFileName(IdUtil.getId());
		fileInsert.setExtension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
		fileInsert.setFileUrl(
				prefix + prefixSecondBig + "/" + fileInsert.getFileName() + "." + fileInsert.getExtension());
		fileInsert.setStatus(FileStatus.READY.getCode());
		fileInsert.setSize(file.getSize());
		fileInsert.setCreateTime(new Date());
		fileInsert.setUpdateTime(new Date());
		filesService.save(fileInsert);

		File fileSave = new File(fileInsert.getFileUrl());
		if (!fileSave.getParentFile().exists()) { // 如果文件的目录不存在
			fileSave.getParentFile().mkdirs(); // 创建目录
		}
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fis = (FileInputStream) file.getInputStream();
			fos = new FileOutputStream(fileSave);
			byte[] temp = new byte[1024];
			int i = fis.read(temp);
			while (i != -1) {
				fos.write(temp, 0, temp.length);
				fos.flush();
				i = fis.read(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@ApiOperation(value = "图片展示")
	@GetMapping(value = "/getPhoto")
	public void getPhoto(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("fileCode") String fileCode) {
		Files file = filesService.findById(fileCode);
		getPhotoByPrefix(file.getFileUrl(), response, request);
	}

	
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
	
	@SuppressWarnings("resource")
	private void getPhotoByPrefix(String fileUrl, HttpServletResponse response, HttpServletRequest request) {
		if (StringUtils.isEmpty(fileUrl)) {
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
				FileInputStream fis = new FileInputStream(fileUrl);
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
