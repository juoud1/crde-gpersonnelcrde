package gpersonnelcrde.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gpersonnelcrde.service.StockageFichiersImagesService;
import jakarta.servlet.http.HttpServletRequest;

//@Controller
public class StockageFichiersImagesController {
	private static final Logger logger = LoggerFactory.getLogger(StockageFichiersImagesController.class);
	
	private final StockageFichiersImagesService stockageFichiersImagesService;

	public StockageFichiersImagesController(StockageFichiersImagesService stockageFichiersImagesService) {
		this.stockageFichiersImagesService = stockageFichiersImagesService;
		logger.info("composant-de-présenataion de service de stockage des signatures et photos initialisé avec succès!");
	}

	@GetMapping("/images.html")
	public String getFichiersPhotosSignatures(HttpServletRequest request, Model model){

		return null;
	}

	@PostMapping("/images.html")
	public String televerserPhotoSignature(@RequestParam("fichier") MultipartFile fichier, @RequestParam(required = false) String employeMatricule, RedirectAttributes redirectAttributes){

		return null;
	}

	@GetMapping("/images.html/{nomFichier}")
	public String getFichierPhotoSignatureByNomFichier(@RequestParam("nomFichier") String nomFichier, HttpServletRequest request, Model model){

		return null;
	}

}
