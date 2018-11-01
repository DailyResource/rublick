package online.rubick.applications.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import io.swagger.annotations.Api;

@Api(description="验证码接口")
@RestController
public class VerificationController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired  
	DefaultKaptcha defaultKaptcha; 
	
	@Value("${server.contextPath}")
	private String contextPath;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/verifycode")
	public void verifycode(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)throws Exception {
		 byte[] captchaChallengeAsJpeg = null;    
         ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();    
         try {    
			 //生产验证码字符串并保存到session中  
			 String createText = defaultKaptcha.createText();  
			 httpServletRequest.getSession().setAttribute("vrifyCode", createText);  
			 //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中  
			 BufferedImage challenge = defaultKaptcha.createImage(createText);  
			 ImageIO.write(challenge, "jpg", jpegOutputStream);  
			 } catch (IllegalArgumentException e) {    
			     httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);    
			     return;    
			 }   
     
			 //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组  
			 captchaChallengeAsJpeg = jpegOutputStream.toByteArray();    
			 httpServletResponse.setHeader("Cache-Control", "no-store");    
			 httpServletResponse.setHeader("Pragma", "no-cache");    
			 httpServletResponse.setDateHeader("Expires", 0);    
			 httpServletResponse.setContentType("image/jpeg");    
			 ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();    
			 responseOutputStream.write(captchaChallengeAsJpeg);    
			 responseOutputStream.flush();    
			 responseOutputStream.close(); 
	}
	
}
