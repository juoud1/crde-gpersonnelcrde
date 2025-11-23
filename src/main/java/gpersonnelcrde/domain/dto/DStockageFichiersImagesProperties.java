package gpersonnelcrde.domain.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** 
* Composant de description des propriétés du dossier
* de stockage des signatures et images des employés
*/

@ConfigurationProperties("stockagecrde")
public record DStockageFichiersImagesProperties(String emplacement) {
	/*private static final Logger logger = LoggerFactory.getLogger(DossierStockageFichiersImagesProperties.class);
	private static String location = "/dossier-crde-images";

	public DossierStockageFichiersImagesProperties(String emplacement, boolean parDefaut){
		this(Objects.isNull(emplacement) ? location : emplacement);
		logger.info("record de dossier stockage des sigantures et images initialisé avec succès".toUpperCase());
	}*/
} 