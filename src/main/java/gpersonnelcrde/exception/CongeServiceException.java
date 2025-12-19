package gpersonnelcrde.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CongeServiceException extends Exception {
	private static Logger logger = LoggerFactory.getLogger(CongeServiceException.class);
	
	public CongeServiceException (String msge){
		super(msge);
		logger.warn("CongeServiceException : {}", msge);
	}

	public CongeServiceException (String msge, Throwable throwable){
		super(msge, throwable);
		logger.warn("CongeServiceException : {}", msge, throwable);
	}

}
