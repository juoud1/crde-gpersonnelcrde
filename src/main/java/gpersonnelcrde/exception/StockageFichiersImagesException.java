package gpersonnelcrde.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockageFichiersImagesException extends Exception {
private static Logger logger = LoggerFactory.getLogger(StockageFichiersImagesException.class);
	
	public StockageFichiersImagesException(String msge){
		super(msge);
		logger.warn("DossierStockageFichiersImagesException : {}", msge);
	}

	public StockageFichiersImagesException(String msge, Throwable throwable){
		super(msge, throwable);
		logger.warn("DossierStockageFichiersImagesException : {}\n {}", msge, throwable);
	}
}
