package by.htp.news.service.validation;

public class NewsValidatorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public NewsValidatorException() {
		super();
	}
	
	public NewsValidatorException(String message) {
		super(message);
	}
	
	public NewsValidatorException(Exception e) {
		super(e);
	}	
	
	public NewsValidatorException(String message, Exception e) {
		super(message, e);
	}
}
