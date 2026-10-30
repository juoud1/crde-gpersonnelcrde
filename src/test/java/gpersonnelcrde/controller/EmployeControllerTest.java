package gpersonnelcrde.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import gpersonnelcrde.service.StatusService;
import gpersonnelcrde.service.StockageFichiersImagesService;
import gpersonnelcrde.service.TypeEmployeService;

class EmployeControllerTest {
	private StatusService statusService;
	private TypeEmployeService typeEmployeService;
	private FonctionService fonctionService;
	private LieuAffectationService lieuAffectationService;
	private EmployeService employeService;
	private StockageFichiersImagesService stockageFichiersImagesService;
	private EmployeController employeController;

	@BeforeEach
	void setUp() {
		statusService = mock(StatusService.class);
		typeEmployeService = mock(TypeEmployeService.class);
		fonctionService = mock(FonctionService.class);
		lieuAffectationService = mock(LieuAffectationService.class);
		employeService = mock(EmployeService.class);
		stockageFichiersImagesService = mock(StockageFichiersImagesService.class);
		employeController = new EmployeController(statusService, typeEmployeService, fonctionService,
				lieuAffectationService, employeService, stockageFichiersImagesService);
	}

	@Test
	void getEmployesLoadsReferenceDataAndSeparatesEmployeesInService() throws Exception {
		var actif = new EmployeDto();
		actif.setStatus("ACT");
		var archive = new EmployeDto();
		archive.setStatus("AUT");
		when(statusService.getAllStatus()).thenReturn(List.of());
		when(typeEmployeService.getAllTypeEmp()).thenReturn(List.of());
		when(fonctionService.getAllFonction()).thenReturn(List.of());
		when(lieuAffectationService.getAllLieuAffect()).thenReturn(List.of());
		when(employeService.getAllEmploye()).thenReturn(List.of(actif, archive));
		var model = new ExtendedModelMap();

		var view = employeController.getEmployes(new MockHttpServletRequest(), model);

		assertEquals("gemployecrdelist", view);
		assertEquals(List.of(actif, archive), model.getAttribute("allEmployes"));
		assertEquals(List.of(actif), model.getAttribute("employesEnSvce"));
		assertNotNull(model.getAttribute("allStatus"));
		assertNotNull(model.getAttribute("allTypeEmp"));
		assertNotNull(model.getAttribute("allFonctions"));
		assertNotNull(model.getAttribute("allLieuAffect"));
	}

	@Test
	void addEmployeFormLoadsReferenceData() throws Exception {
		when(statusService.getAllStatus()).thenReturn(List.of());
		when(typeEmployeService.getAllTypeEmp()).thenReturn(List.of());
		when(fonctionService.getAllFonction()).thenReturn(List.of());
		when(lieuAffectationService.getAllLieuAffect()).thenReturn(List.of());
		var model = new ExtendedModelMap();

		var view = employeController.addEmploye(new MockHttpServletRequest(), model);

		assertEquals("gemployecrde", view);
		assertNotNull(model.getAttribute("allStatus"));
		assertNotNull(model.getAttribute("allTypeEmp"));
		assertNotNull(model.getAttribute("allFonctions"));
		assertNotNull(model.getAttribute("allLieuAffect"));
	}

	@Test
	void addEmployeCreatesEmployeeAndReturnsRecap() throws Exception {
		var employe = new EmployeDto();
		employe.setEmpMatricule("EMP-1");
		when(employeService.createEmploye("M.", "Nom", "Prenom", null, null, null, "PER", "EMP-1",
				null, null, "ACT", "FON", null, null, null, null, null, "LIEU", null, null, null, null))
				.thenReturn(Optional.of(employe));
		var model = new ExtendedModelMap();

		var view = employeController.addEmploye("M.", "Nom", "Prenom", "PER", "EMP-1", null, null, null,
				null, null, null, null, null, null, "ACT", "FON", null, "LIEU", null, null, null,
				new MockHttpServletRequest(), model);

		assertEquals("gemployecrderecap", view);
		assertSame(employe, model.getAttribute("savedEmploye"));
		assertEquals("Création de l'employé effectuée avec succès.", model.getAttribute("resultTraitement"));
	}

	@Test
	void serveFileReturnsNotFoundWhenResourceIsMissing() throws Exception {
		when(stockageFichiersImagesService.chargerFichierCrdeAsResource("absent.png")).thenReturn(null);

		var response = employeController.serveFile("absent.png");

		assertEquals(404, response.getStatusCode().value());
	}

	@Test
	void serveFileReturnsResourceAsAttachment() throws Exception {
		var resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("portrait.png");
		when(stockageFichiersImagesService.chargerFichierCrdeAsResource("portrait.png")).thenReturn(resource);

		var response = employeController.serveFile("portrait.png");

		assertEquals(200, response.getStatusCode().value());
		assertSame(resource, response.getBody());
		assertEquals("attachment; filename=\"portrait.png\"",
				response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
	}

	@Test
	void getEmployeByNumInterneReturnsEditView() throws Exception {
		var employe = new EmployeDto();
		employe.setEmpMatricule("EMP-1");
		stubEmployeeLookups(employe);
		var model = new ExtendedModelMap();

		var view = employeController.getEmployeByNumInterne("EMP-1", "mdific", new MockHttpServletRequest(), model);

		assertEquals("gemployecrdemaj", view);
		assertSame(employe, model.getAttribute("savedEmploye"));
	}

	@Test
	void getEmployeByNumInterneReturnsRecapForConsultation() throws Exception {
		var employe = new EmployeDto();
		stubEmployeeLookups(employe);
		var model = new ExtendedModelMap();

		var view = employeController.getEmployeByNumInterne("EMP-1", "cnsult", new MockHttpServletRequest(), model);

		assertEquals("gemployecrderecap", view);
		assertEquals("Fiche de l'employé ou stagiaire", model.getAttribute("traitement"));
	}

	@Test
	void getEmployeByNumInterneRedirectsForUnsupportedOperation() throws Exception {
		stubEmployeeLookups(new EmployeDto());

		var view = employeController.getEmployeByNumInterne("EMP-1", "autre", new MockHttpServletRequest(),
				new ExtendedModelMap());

		assertEquals("redirect:/employes-crde.html", view);
	}

	@Test
	void deleteEmployeRedirectsToEmployeeList() {
		var view = employeController.deleteEmployeByNumInterne("EMP-1", new MockHttpServletRequest(),
				new ExtendedModelMap());

		assertEquals("redirect:/employes-crde.html", view);
	}

	@Test
	void updateEmployeReturnsRecapWithMatricule() {
		var model = new ExtendedModelMap();

		var view = employeController.updateEmployeByNumInterne("INT-1", "M.", "Nom", "Prenom", "PER", "EMP-1",
				null, null, null, null, null, null, null, null, null, null, "FON", null, "LIEU", null,
				null, null, null, null, new MockHttpServletRequest(), model);

		assertEquals("gemployecrderecap", view);
		var savedEmploye = (EmployeDto) model.getAttribute("savedEmploye");
		assertEquals("EMP-1", savedEmploye.getEmpMatricule());
		assertEquals("modification de l'employé/stagiaire EMP-1", model.getAttribute("traitement"));
	}

	@Test
	void handleStorageFileNotFoundReturnsNotFound() {
		var response = employeController.handleStorageFileNotFound(new StockageFichiersImagesException("introuvable"));

		assertEquals(404, response.getStatusCode().value());
	}

	private void stubEmployeeLookups(EmployeDto employe) throws Exception {
		when(employeService.getEmployeByMatricule("EMP-1")).thenReturn(Optional.of(employe));
		when(statusService.getAllStatus()).thenReturn(List.of());
		when(typeEmployeService.getAllTypeEmp()).thenReturn(List.of());
		when(fonctionService.getAllFonction()).thenReturn(List.of());
		when(lieuAffectationService.getAllLieuAffect()).thenReturn(List.of());
	}
}