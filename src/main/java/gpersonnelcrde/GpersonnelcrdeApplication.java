package gpersonnelcrde;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import gpersonnelcrde.domain.dto.DStockageFichiersImagesProperties;
import gpersonnelcrde.service.StockageFichiersImagesService;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(DStockageFichiersImagesProperties.class)
public class GpersonnelcrdeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpersonnelcrdeApplication.class, args);
	}

	@Bean
	CommandLineRunner initDossierPhotosSignatures(StockageFichiersImagesService dossierStockageFichiersImagesService ) {
		return args -> dossierStockageFichiersImagesService.creerDossierPhotosSignatures();
	}
}
