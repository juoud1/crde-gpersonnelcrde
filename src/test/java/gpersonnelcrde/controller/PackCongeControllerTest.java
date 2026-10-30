package gpersonnelcrde.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.PackCongeDto;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.PackCongeService;

class PackCongeControllerTest {
	private PackCongeService packCongeService;
	private EmployeService employeService;
	private PackCongeController controller;

	@BeforeEach
	void setUp() {
		packCongeService = mock(PackCongeService.class);
		employeService = mock(EmployeService.class);
		controller = new PackCongeController(packCongeService, employeService);
	}

	@Test
	void getGestCongesLoadsCongesAndEmployees() throws Exception {
		var conge = new PackCongeDto();
		var employe = new EmployeDto();
		var conges = List.of(conge);
		var employes = List.of(employe);
		when(packCongeService.getAllPackConges()).thenReturn(conges);
		when(employeService.getAllEmploye()).thenReturn(employes);
		var model = new ExtendedModelMap();

		var view = controller.getGestConges(new MockHttpServletRequest(), model);

		assertEquals("gcongecrdelist", view);
		assertSame(conges, model.getAttribute("allConges"));
		assertSame(employes, model.getAttribute("allEmployes"));
	}

	@Test
	void getCongeConfiguresLeaveAndExitAuthorization() throws Exception {
		when(packCongeService.getAllPackConges()).thenReturn(List.of());
		when(employeService.getAllEmploye()).thenReturn(List.of());
		var model = new ExtendedModelMap();

		var view = controller.getConge("cnge-et-as", new MockHttpServletRequest(), model);

		assertEquals("gcongecrde", view);
		assertEquals("Congé et autorisation sortie", model.getAttribute("typCngeValue"));
		assertEquals("Congé et autorisation de sortie", model.getAttribute("typCngeText"));
	}

	@Test
	void getCongeConfiguresRegularLeave() throws Exception {
		when(packCongeService.getAllPackConges()).thenReturn(List.of());
		when(employeService.getAllEmploye()).thenReturn(List.of());
		var model = new ExtendedModelMap();

		var view = controller.getConge("conge", new MockHttpServletRequest(), model);

		assertEquals("gcongecrde", view);
		assertEquals("Congé", model.getAttribute("typCngeValue"));
		assertEquals("Congé", model.getAttribute("typCngeText"));
	}

	@Test
	void addCongeReturnsRecapWithCreationDetails() throws Exception {
		var dateDebut = LocalDate.of(2026, 6, 1);
		var dateFin = LocalDate.of(2026, 6, 14);
		var dateDepart = LocalDate.of(2026, 6, 15);
		var dateRetour = LocalDate.of(2026, 6, 20);
		var savedConge = new PackCongeDto();
		savedConge.setNumConge("CON-1");
		when(packCongeService.savePackCongeEmploye("EMP-1", "conge", dateDebut, dateFin, "infos",
				"NS-1", "AS-1", dateDepart, dateRetour, "Bangui", "RCA", "motif", "EMP-2"))
				.thenReturn(savedConge);
		var model = new ExtendedModelMap();

		var view = controller.addConge("EMP-1", "conge", dateDebut, dateFin, "infos", "EMP-2", "NS-1",
				"AS-1", dateDepart, dateRetour, "Bangui", "RCA", "motif", new MockHttpServletRequest(), model);

		assertEquals("gcongecrderecap", view);
		assertSame(savedConge, model.getAttribute("savedConge"));
		assertEquals("création de nouveau congé", model.getAttribute("traitement"));
		assertEquals("Création du pack congé et aut. de sortie de l'employé effectuée avec succès.",
				model.getAttribute("resultTraitement"));
	}

	@Test
	void getPackCongeByNumNoteLoadsCongeAndActiveEmployees() throws Exception {
		var conge = new PackCongeDto();
		conge.setNumConge("CON-1");
		var actif = new EmployeDto();
		actif.setStatus("ACT");
		var archive = new EmployeDto();
		archive.setStatus("AUT");
		var employes = List.of(actif, archive);
		when(packCongeService.getPackCongeByNumNoteService("CON-1")).thenReturn(Optional.of(conge));
		when(employeService.getAllEmploye()).thenReturn(employes);
		var model = new ExtendedModelMap();

		var view = controller.getPackCongeByNumNoteSvceConge("CON-1", new MockHttpServletRequest(), model);

		assertEquals("gcongecrdemaj", view);
		assertSame(conge, model.getAttribute("savedConge"));
		assertEquals(employes, model.getAttribute("allEmployes"));
		assertEquals(List.of(actif), model.getAttribute("employesEnSvce"));
	}

	@Test
	void getPackCongesByEmpMatriculeLoadsEmployeeLeaveList() {
		var conges = List.of(new PackCongeDto());
		when(packCongeService.getPackCongeByEmployeMatricule("EMP-1")).thenReturn(conges);
		var model = new ExtendedModelMap();

		var view = controller.getPackCongesByEmpMatricule("EMP-1", new MockHttpServletRequest(), model);

		assertEquals("gcongecrdemaj", view);
		assertSame(conges, model.getAttribute("savedCongesEmploye"));
	}

	@Test
	void getCongesByEmpMatriculeAddsHistoryChoiceWhenNotHistory() {
		var conges = List.of(new PackCongeDto());
		when(packCongeService.getPackCongeByEmployeMatricule("EMP-1")).thenReturn(conges);
		var model = new ExtendedModelMap();

		var view = controller.getCongesByEmpMatricule("EMP-1", "a-venir", new MockHttpServletRequest(), model);

		assertEquals("gcongesemphistoriq", view);
		assertSame(conges, model.getAttribute("savedCongesEmploye"));
		assertEquals("EMP-1", model.getAttribute("congeEmpMatricule"));
		assertEquals("a-venir", model.getAttribute("savedChoixStr"));
	}

	@Test
	void getCongesByEmpMatriculeOmitsHistoryChoiceForHistory() {
		when(packCongeService.getPackCongeByEmployeMatricule("EMP-1")).thenReturn(List.of());
		var model = new ExtendedModelMap();

		controller.getCongesByEmpMatricule("EMP-1", "hist", new MockHttpServletRequest(), model);

		assertEquals(null, model.getAttribute("savedChoixStr"));
	}
}