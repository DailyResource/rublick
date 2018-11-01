package online.rubick.applications.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * 全局异常处理
 * 
 * @author admin
 */
@RestControllerAdvice
public class ExceptionAdvice {
	private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ExceptionData handleException(Exception e) {
		ExceptionData exceptionData = null;
		String message = null;
		try {
			if (e == null) {
				log.error(
						"Exception is null   -->   online.rubick.applications.exception.ExceptionAdvice.handleException");
				exceptionData = new ExceptionData(HttpStatus.BAD_REQUEST.value(), "发生未知的错误");
			} else {
				log.error(e.getMessage(), e);
				if (e instanceof ConstraintViolationException) {
					Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e)
							.getConstraintViolations();
					ConstraintViolation<?> violation = violations.iterator().next();
					message = violation.getMessage();
					exceptionData = new ExceptionData(HttpStatus.BAD_REQUEST.value(), message);
				} else if (e instanceof MethodArgumentNotValidException) {
					message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError()
							.getDefaultMessage();
					exceptionData = new ExceptionData(HttpStatus.BAD_REQUEST.value(), message);
				} else if (e instanceof ApplicationException) {
					ExceptionInfo info = ((ApplicationException) e).getInfo();
					Integer code = info.getCode();
					message = info.getMessage();
					exceptionData = new ExceptionData(code == null ? HttpStatus.BAD_REQUEST.value() : code,
							message == null ? "未知错误" : message);
				}
			}
			if (exceptionData == null) {
				return new ExceptionData(HttpStatus.BAD_REQUEST.value(), "未知错误");
			}
		} catch (Exception e1) {
			log.error("unknown error", e);
			return new ExceptionData(HttpStatus.BAD_REQUEST.value(), "未知错误");
		}
		return exceptionData;
	}
}
