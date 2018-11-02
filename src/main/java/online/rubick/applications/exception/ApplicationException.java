package online.rubick.applications.exception;

import org.springframework.core.NestedRuntimeException;

import online.rubick.applications.entity.enums.EnumUnit;

public class ApplicationException extends NestedRuntimeException implements ExceptionInfoGetter {

	private static final long serialVersionUID = 1L;

	private Integer code ;
    
    private String content;

    /**
     * 用枚举类来定义异常
     * 
     * @param enumUnit
     */
    public ApplicationException(EnumUnit enumUnit) {
        super(enumUnit.getDescription());

        this.code = Integer.parseInt(enumUnit.getCode());
        this.content = enumUnit.getDescription();
    }
    
    public ApplicationException(String content) {
        super(content);

        this.content = content;
    }

    public ApplicationException(String content, Throwable throwable) {
        super(content, throwable);

        this.content = content;
    }

    public ApplicationException(Integer code, String content) {
        super(content);

        this.code = code;
        this.content = content;
    }

    public ApplicationException(Integer code, String content, Throwable throwable) {
        super(content, throwable);

        this.code = code;
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
	@Override
	public ExceptionInfo getInfo() {
		return new ExceptionInfo(this.code, this.content);
	}

}
