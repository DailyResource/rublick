package online.rubick.applications.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import online.rubick.applications.entity.sys.SysMessage;

/**
 * 短信发送服务类. (Spring service)
 * 
 * @author
 */
@Component
public class SmsSendService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${SMS.TEMPLET.FORGETPASSWORD}")
	private String tplId;// 验证码模板编号
	
	@Value("${SMS.TEMPLET.WARN}")
	private String warnlId;// 告警模板编号
	
	@Value("${SMS.SIGNNAME}")
	private String signName;// 短信签名
	
	@Value("${SMS.message}")
	private boolean message;// 短信发送标识

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	/**
	 * 阿里密钥ID
	 */
	@Value("${SMS.ACCESSKEYID}")
	private  String accessKeyId;
	
	/**
	 * 阿里密钥
	 */
	@Value("${SMS.ACCESSKEYSECRET}")
	private  String accessKeySecret;

	/**
	 * 发送验证码
	 * 
	 * @param mobile
	 * @param checkCode
	 */
	public SendSmsResponse SmsSend(String mobile, String checkCode) {
		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers(mobile);
		request.setTemplateCode(tplId);
		request.setTemplateParam("{\"code\":\"" + checkCode + "\"}");
		try {
			return sendSms(request);
		} catch (ClientException e) {
			log.error(e.getErrMsg());
		}
		return new SendSmsResponse();
	}

	/**
	 * 发送 告警信息
	 * 
	 * @param entity
	 */
	public SendSmsResponse warningMessage(SysMessage entity) {
		SendSmsResponse sendSmsResponse =new SendSmsResponse();
		String[] contents = entity.getContent().split("--");
		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers(entity.getContactTel());
		request.setTemplateCode(warnlId);
		request.setOutId(entity.getId());
		request.setTemplateParam("{\"boxid\":\"" + contents[0] + "\", \"address\":\"" + contents[1] + "\",\"number\":\""+contents[2]+"\"}");
		try {
			sendSmsResponse = sendSms(request);
			log.info(sendSmsResponse.getMessage());
		} catch (ClientException e) {
			sendSmsResponse.setCode(e.getErrCode());
			sendSmsResponse.setMessage(e.getErrMsg());
		}
		return sendSmsResponse;
	}

	/**
	 * 发送验证码
	 * 
	 * @param mobile
	 * @param checkCode
	 */
	public SendSmsResponse smsSend(String mobile, String checkCode) {
		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers(mobile);
		request.setTemplateCode(tplId);
		request.setTemplateParam("{\"code\":\"" + checkCode + "\"}");
		try {
			return sendSms(request);
		} catch (ClientException e) {
			log.error(e.getErrMsg());
		}
		return new SendSmsResponse();
	}

	public  SendSmsResponse sendSms(SendSmsRequest request) throws ClientException {// 设置超时时间-可自行调整
		if(!message) {
			return new SendSmsResponse();
		}
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		// SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		// request.setPhoneNumbers("1500000000");
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		// request.setTemplateCode("SMS_1000000");
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		// request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		return sendSmsResponse;
		/*
		 * if (sendSmsResponse.getCode() != null &&
		 * sendSmsResponse.getCode().equals("OK")) { // 请求成功 }
		 */
	}

	public static String createCheckCode() {
		// 验证码 生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
	    Random random = new Random();
		StringBuffer buff = new StringBuffer("");
		for (int i = 0; i < 6; i++) {
		    buff.append(random.nextInt(10));
        }
		return buff.toString();
	}
}
