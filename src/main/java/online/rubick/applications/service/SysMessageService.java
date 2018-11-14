package online.rubick.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

import online.rubick.applications.Query.MessageManagementQuery;
import online.rubick.applications.dao.sys.SysMessageMapper;
import online.rubick.applications.entity.sys.SysMessage;
import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.vo.sys.MessageVo;
@Service
public class SysMessageService {

	@Autowired
	private SysMessageMapper messageMapper;

	@Autowired
	private SmsSendService smsSendService;

	public int save(SysMessage entity) {
		return messageMapper.insert(entity);
	}

	public int delete(String id) {
		return messageMapper.deleteByPrimaryKey(id);
	}

	public int update(SysMessage entity) {
		return messageMapper.updateByPrimaryKeySelective(entity);
	}

	public SysMessage findById(String id) {
		return messageMapper.selectByPrimaryKey(id);
	}

	public List<SysMessage> getAll() {
		return messageMapper.getAll();
	}

	public Page<SysMessage> findMessagePage(String messageType, String keyword, Pageable pageable) {
		return messageMapper.findMessagePage(messageType, keyword, pageable);
	}

	/**
	 * 消息管理模块条件分页查询
	 */
	public Page<MessageVo> selectAllMessage(MessageManagementQuery messageQuery, Pageable pageable) {
		return messageMapper.selectAllMessage(messageQuery, pageable);
	}

	public int updateReadFlagById(String readFlag, String optionUser, String opentime, String Id) {
		return messageMapper.updateReadFlagById(readFlag, optionUser, opentime, Id);
	}

	public List<String> selectAllMessageByAllNORead(MessageManagementQuery messageQuery) {
		return messageMapper.selectAllMessageByAllNORead(messageQuery);
	}

	public String sendMessCode(String mobile) {
		String checkCode = SmsSendService.createCheckCode();
		SendSmsResponse response = smsSendService.smsSend(mobile, checkCode);
		if ("OK".equals(response.getCode())) {
			return checkCode;
		} else {
			throw new ApplicationException("验证码发送失败，请稍后重试");
		}
	}

}
