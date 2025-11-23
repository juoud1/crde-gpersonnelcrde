package gpersonnelcrde.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import gpersonnelcrde.domain.dto.DStockageFichiersImagesProperties;
import gpersonnelcrde.exception.StockageFichiersImagesException;

/**
 * Composant de gestion de stockage des signatures et photos des employés de CRDE
 */

@Service
@Transactional
public class StockageFichiersImagesService {
	private static final Logger logger = LoggerFactory.getLogger(StockageFichiersImagesService.class);
	private static final String OS_USER_DIR = System.getProperty("user.dir");

	private final Path rootCrdePath;

	@Autowired
	public StockageFichiersImagesService(DStockageFichiersImagesProperties dossierProperties){
		this.rootCrdePath = Paths.get(Paths.get(OS_USER_DIR).getParent().toString(), dossierProperties.emplacement());
		logger.info("composant service de dossier stockage des sigantures et images initialisé avec succès".toUpperCase());
	}

	public Path stockerFichierCrde (final MultipartFile fichier, List<String> donneesEmploye, boolean statusFichier) throws StockageFichiersImagesException{
		logger.info("Destination principale pour le fichier encours d'enregistrement est {}", this.rootCrdePath);
		Long result = null;
		
		try {
			//File destinationFile = this.rootCrdePath.resolve(Paths.get(fichier.getOriginalFilename())).normalize().toFile();
			Path pathOriginal = Paths.get(fichier.getOriginalFilename());
			var pathRenommer = renommerPhoto(pathOriginal, donneesEmploye);
			Path destinationPath = this.rootCrdePath.resolve(pathRenommer).normalize().toAbsolutePath();
			//Path destinationPath = this.rootCrdePath.resolve(Paths.get(fichier.getOriginalFilename())).normalize().toAbsolutePath();
			
			if (!destinationPath.getParent().equals(this.rootCrdePath.toAbsolutePath())){
				throw new StockageFichiersImagesException("Impossible de créer le fichier à l'extérieur du dossier autorisé par CRDE.");
			}

			try (InputStream inputStream = fichier.getInputStream()) {
				result = Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
				logger.info("PHOTO/SIGNATURE {} STOCKÉE AVEC SUCCÈS.", result);
			}

			if (result <= 0) {
				throw new StockageFichiersImagesException("La création du fichier sur le disque a échouée");
			}

			return destinationPath;
		
		} catch (IOException ioe) {
			throw new StockageFichiersImagesException("Échec lors de sauvegarde du fichier.", ioe);
		}
	}

	public Path chargerFichierCrde(String nomFichier) {
		logger.info("PHOTO/SIGNATURE {} CHARGÉE AVEC SUCCÈS.", nomFichier);
		return this.rootCrdePath.resolve(nomFichier);
	}

	public Resource chargerFichierCrdeAsResource(String nomFichier) throws StockageFichiersImagesException, MalformedURLException {
		Path fichier = chargerFichierCrde(nomFichier);
		Resource resource = new UrlResource(fichier.toUri());
		if (resource.exists() || resource.isReadable()){
			logger.info("PHOTO/SIGNATURE {} CHARGÉE COMME RESSOURCE AVEC SUCCÈS.", nomFichier);
			return resource;
		} else {
			throw new StockageFichiersImagesException("Le fichier n'est pas lisible");
		}
	}

	public Stream<Path> chargerFichiersCrde() throws StockageFichiersImagesException{
		try {
			logger.info(" CHARGEMENT DE TOUTES LES PHOTOS/SIGNATURES EFFECTUÉ AVEC SUCCÈS.");

			return Files.walk(this.rootCrdePath, 1)
						.filter(path -> !path.equals(this.rootCrdePath))
						.map(this.rootCrdePath::relativize);
						
		} catch (IOException ioe) {
			throw new StockageFichiersImagesException("Échec de charger toutes les photos/signatures de CRDE.", ioe);
		}
	}

	public Path creerDossierPhotosSignatures() throws StockageFichiersImagesException{
		//logger.info("RÉPERTOIRE SYSTÈME PRINCIPAL DE L'UTILISATEUR CRDE POUR LA CRÉATION DU DOSSIER : {}", OS_USER_DIR);
		//logger.info("RÉPERTOIRE POUR LA DESTINATION DES PHOTOS ET SIGNATURES D'EMPLOYÉS CRDE À CRÉER : {}", this.rootCrdePath);

		try {
			Resource resource = new UrlResource(this.rootCrdePath.toAbsolutePath().toUri());
			logger.info( resource.exists() ? "LE DOSSIER DES PHOTOS ET SIGNATURES DES EMPLOYÉS DE CRDE EXISTE DÉJA" : "CRÉATION DE DOSSIER DES PHOTOS ET SIGNATURES DES EMPLOYÉS DE CRDE ENCOURS ...");
			
			var dossier = !resource.exists() && this.rootCrdePath.isAbsolute() ? Files.createDirectories(this.rootCrdePath) : this.rootCrdePath;
			logger.info("DOSSIER DES PHOTOS ET SIGNATURES DES EMPLOYÉS DE CRDE {} CRÉÉ AVEC SUCCÈS.", dossier.toAbsolutePath());

			return dossier.toAbsolutePath();
		} catch (IOException ioe) {
			throw new StockageFichiersImagesException("Impossible de créer le dossier.", ioe);
		}
	}

	public void supprimerPhotosSignatures() {
		boolean result = FileSystemUtils.deleteRecursively(this.rootCrdePath.toFile());
		logger.info(result ? "DOSSIER {} SUPPRIMÉ AVEC SUCCÈS." : "IMPOSSIBLE DE SUPPRIMER LE DOSSIER {}", this.rootCrdePath.toAbsolutePath());
	}

	public boolean supprimerPhotoSignature(String nomFichier) throws StockageFichiersImagesException {
		Path destinationPath = this.chargerFichierCrde(nomFichier);
		
		try {
			boolean result = FileSystemUtils.deleteRecursively(destinationPath);
			logger.info(result ? "FICHIER {} SUPPRIMÉ AVEC SUCCÈS" : "IMPOSSIBLE DE SUPPRIMER LE FICHIER {}", nomFichier); 
			
			return result;
		} catch (IOException ioe) {
			throw new StockageFichiersImagesException("IMPOSSIBLE DE SUPPRIMER LE FICHIER.", ioe);
		}
		
	}

	private Path renommerPhoto(Path fichier, List<String> elts) {
		logger.info("NOM FICHIER AVANT : {}", fichier);
		String fichierNom = fichier.toFile().getName();

		// Extract file extension
		int indexPoint = fichierNom.lastIndexOf('.');
		String fichierExtension = indexPoint>1 ? fichierNom.substring(indexPoint) : "";
		
		// Create new file name
		String nveauNomFichier = getNouveauNomFichier(elts) + fichierExtension;
		File fichierRenommer = new File(nveauNomFichier);
		logger.info("NOM FICHIER APRÈS : {}", nveauNomFichier);

		return fichierRenommer.toPath();
	}

	private String getNouveauNomFichier(List<String> chaines){
		return String.join("_", chaines.get(0), chaines.get(1), chaines.get(2));
	}
}
