package online.rubick.applications.util;

import java.io.Serializable;

public class BaseRespons implements Serializable {

	private static final long serialVersionUID = 9070496574704085765L;
	String code;
	String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public BaseRespons(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BaseRespons() {
		super();
	}

	public static BaseRespons ok() {
		return new BaseRespons("200", "success");
	}

}
