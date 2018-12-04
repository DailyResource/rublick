package online.rubick.applications.controller.wx;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.entity.rubick.Files;
import online.rubick.applications.enums.rubick.FileStatus;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.service.rubick.FilesService;
import online.rubick.applications.vo.rubick.FilesVO;

@Api(description = "文件管理")
@RestController
@RequestMapping("/wx/file")
@ResponseBody
public class FileController {

	@Value("${file.prefix}")
	private String prefix;

	@Value("${file.prefixSecondSmall}")
	private String prefixSecondSmall;

	@Value("${file.prefixSecondBig}")
	private String prefixSecondBig;

	@Autowired
	private FilesService filesService;

	@ApiOperation(value = "根据分组获取图片集合")
	@GetMapping(value = "/getPhotoList")
	public List<FilesVO> getPhoto(@RequestParam("groupId") String groupId) {
		List<Files> filesList = filesService.findByGroupId(groupId);
		List<FilesVO> list = new ArrayList<>();
		for (Files files : filesList) {
			FilesVO vo = new FilesVO();
			BeanUtils.copyProperties(files, vo);
			vo.setStatusName(FileStatus.getEnumByCode(files.getStatus()).getDescription());
			list.add(vo);
		}
		return list;
	}

	@ApiOperation(value = "图片展示")
	@GetMapping(value = "/getPhoto")
	public void getPhoto(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("fileUrl") String fileUrl, @RequestParam("photoType") String photoType) {
		switch (photoType) {
		case "0":
			getPhotoByPrefix(fileUrl, prefixSecondSmall, response, request);
			break;
		default:
			getPhotoByPrefix(fileUrl, prefixSecondBig, response, request);
			break;
		}
	}

	@SuppressWarnings("resource")
	private void getPhotoByPrefix(String fileUrl, String filePrefixSecond, HttpServletResponse response,
			HttpServletRequest request) {
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
				FileInputStream fis = new FileInputStream(prefix + filePrefixSecond + "/" + fileUrl);
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

	@ApiOperation(value = "上传文件")
	@PostMapping(value = "/upload")
	public void testUploadFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) {
		// 获取上传文件的路径
		String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
		System.out.println("uploadFlePath:" + uploadFilePath);
		// 截取上传文件的文件名
		String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1,
				uploadFilePath.indexOf('.'));
		System.out.println("multiReq.getFile()" + uploadFileName);
		// 截取上传文件的后缀
		String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
		System.out.println("uploadFileSuffix:" + uploadFileSuffix);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fis = (FileInputStream) multiReq.getFile("file1").getInputStream();
			fos = new FileOutputStream(new File(".//uploadFiles//" + uploadFileName + ".") + uploadFileSuffix);
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

	@ApiOperation(value = "上传多个文件")
	@PostMapping(value = "/uploads")
	public void handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					String uploadFilePath = file.getOriginalFilename();
					System.out.println("uploadFlePath:" + uploadFilePath);
					// 截取上传文件的文件名
					String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1,
							uploadFilePath.indexOf('.'));
					System.out.println("multiReq.getFile()" + uploadFileName);
					// 截取上传文件的后缀
					String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1,
							uploadFilePath.length());
					System.out.println("uploadFileSuffix:" + uploadFileSuffix);
					stream = new BufferedOutputStream(new FileOutputStream(
							new File(".//uploadFiles//" + uploadFileName + "." + uploadFileSuffix)));
					byte[] bytes = file.getBytes();
					stream.write(bytes, 0, bytes.length);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (stream != null) {
							stream.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("上传文件为空");
			}
		}
		System.out.println("文件接受成功了");
	}

	@ApiOperation(value = "sftp上传文件")
	@PostMapping(value = "/sftp")
	public String sftp(MultipartFile file) {
		// Sftp上传文件
		// SftpUtil sftp = new SftpUtil("lihe", "lihe2018", "10.0.5.239", 2020);
		// sftp.login();
		// InputStream is = file.getInputStream();
		// sftp.upload("/data/work", file.getOriginalFilename(), is);
		// sftp.logout();

		// Sftp删除文件
		// SftpUtil sftp = new SftpUtil("lihe", "lihe2018", "10.0.5.239", 2020);
		// sftp.login();
		// sftp.delete(dmsUpdateFile.getUpdateFilePath(),
		// dmsUpdateFile.getUpdateFileName());
		// sftp.logout();

		// 下载
		// byte[] buff = sftp.download("/opt", "start.sh");
		// System.out.println(Arrays.toString(buff));
		return "该方法没有实现";
	}

}
