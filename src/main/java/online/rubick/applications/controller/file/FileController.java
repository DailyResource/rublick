package online.rubick.applications.controller.file;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import online.rubick.applications.util.SftpUtil;

@RestController
public class FileController {
	
	@ApiOperation(value = "图片展示", notes = "图片展示")
	@GetMapping(value="/downloadPhoto")
	public void downloadHeadPhoto(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("photoPath") String photoPath) throws Exception {

		if (StringUtils.isEmpty(photoPath)) {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("没有相关的图片信息");
		} else {
			byte[] downloadFile = new byte[1024];
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			ServletOutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(downloadFile);
			responseOutputStream.flush();
			responseOutputStream.close();
		}
	}
	
	
	@ApiOperation(value = "上传文件", notes = "上传文件")
	@PostMapping(value="/upload")
	public void upload(MultipartFile file) throws Exception {
	   
	   
	     //Sftp上传文件
         SftpUtil sftp = new SftpUtil("lihe", "lihe2018", "10.0.5.239", 2020);
         sftp.login(); 
         InputStream is = file.getInputStream();
         sftp.upload("/data/work", file.getOriginalFilename(), is); 
         sftp.logout();
		
	    // Sftp删除文件
        // SftpUtil sftp = new SftpUtil("lihe", "lihe2018", "10.0.5.239", 2020);
        // sftp.login();
        // sftp.delete(dmsUpdateFile.getUpdateFilePath(),
        // dmsUpdateFile.getUpdateFileName());
        // sftp.logout();
         
        // 下载
        //byte[] buff = sftp.download("/opt", "start.sh");
 	    //System.out.println(Arrays.toString(buff));
	}

}
