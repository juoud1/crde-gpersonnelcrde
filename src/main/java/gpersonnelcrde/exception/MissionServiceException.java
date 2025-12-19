package gpersonnelcrde.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MissionServiceException extends Exception {
	private static Logger logger = LoggerFactory.getLogger(MissionServiceException.class);
	
	public MissionServiceException (String msge){
		super(msge);
		logger.warn("MissionServiceException : {}", msge);
	}

	public MissionServiceException (String msge, Throwable throwable){
		super(msge, throwable);
		logger.warn("MissionServiceException : {}", msge, throwable);
	}

}
