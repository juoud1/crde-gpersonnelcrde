package gpersonnelcrde.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeServiceException extends Exception {
private static Logger logger = LoggerFactory.getLogger(EmployeServiceException.class);
	
	public EmployeServiceException (String msge){
		super(msge);
		logger.warn("EmployeServiceException : {}", msge);
	}

	public EmployeServiceException (String msge, Throwable throwable){
		super(msge, throwable);
		logger.warn("EmployeServiceException : {}", msge, throwable);
	}
}
