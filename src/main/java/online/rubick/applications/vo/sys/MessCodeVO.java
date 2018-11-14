package online.rubick.applications.vo.sys;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "MessCode", description = "短信验证码")
public class MessCodeVO implements Serializable{

    private static final long serialVersionUID = 1L;

    String mobile;
    
    String code;

    String time;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

