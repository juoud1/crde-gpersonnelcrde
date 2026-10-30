package gpersonnelcrde.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.MissionService;

class MissionControllerTest {
	private MissionService missionService;
	private EmployeService employeService;
	private MissionController missionController;

	@BeforeEach
	void setUp() {
		missionService = mock(MissionService.class);
		employeService = mock(EmployeService.class);
		missionController = new MissionController(missionService, employeService);
	}

	@Test
	void getGestMissionsLoadsMissionsAndEmployeesInService() throws Exception {
		var mission = new MissionDto();
		var actif = new EmployeDto();
		actif.setStatus("ACT");
		var archive = new EmployeDto();
		archive.setStatus("AUT");
		when(missionService.getAllMissions()).thenReturn(List.of(mission));
		when(employeService.getAllEmploye()).thenReturn(List.of(actif, archive));
		var model = new ExtendedModelMap();

		var view = missionController.getGestMissions(new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdelist", view);
		assertEquals(List.of(mission), model.getAttribute("allMissions"));
		assertEquals(List.of(actif), model.getAttribute("employesEnSvce"));
	}

	@Test
	void getMissionIndividuelleConfiguresGroupMission() throws Exception {
		stubMissionFormData();
		var model = new ExtendedModelMap();

		var view = missionController.getMissionIndividuelle("miss-grpe", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrde", view);
		assertEquals("Mission de groupe", model.getAttribute("typMissValue"));
		assertEquals("", model.getAttribute("paysResidenceText"));
	}

	@Test
	void getMissionIndividuelleConfiguresIndividualMission() throws Exception {
		stubMissionFormData();
		var model = new ExtendedModelMap();

		var view = missionController.getMissionIndividuelle("individuelle", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrde", view);
		assertEquals("Mission d'un employé", model.getAttribute("typMissText"));
		assertEquals("Rép. Centrafricaine", model.getAttribute("paysResidenceText"));
	}

	@Test
	void addMissionGroupeStoresFormModelInSession() throws Exception {
		stubMissionFormData();
		var request = new MockHttpServletRequest();
		var model = new ExtendedModelMap();

		var view = missionController.addMissionGroupe(request, model);

		assertEquals("gmissioncrde", view);
		assertSame(model, request.getSession().getAttribute("modelMission"));
	}

	@Test
	void addMissionIndividuelleStoresFormModelInSession() throws Exception {
		stubMissionFormData();
		var request = new MockHttpServletRequest();
		var model = new ExtendedModelMap();

		var view = missionController.addMissionIndividuelle(request, model);

		assertEquals("gmissioncrde", view);
		assertSame(model, request.getSession().getAttribute("modelMission"));
	}

	@Test
	void addMissionSavesSingleMissionAndReturnsRecap() {
		var dateDepart = LocalDate.of(2026, 3, 1);
		var dateRetour = LocalDate.of(2026, 3, 5);
		var mission = new MissionDto();
		when(missionService.saveMission("formation", "professionnel", dateDepart, dateRetour,
				"Bangui", "RCA", "motif", "infos", "ORD-1", "individuelle")).thenReturn(mission);
		var model = new ExtendedModelMap();

		var view = missionController.addMission("formation", "professionnel", dateDepart, dateRetour, "Bangui",
				"RCA", "motif", "infos", "ORD-1", "individuelle", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdeRecap", view);
		assertSame(mission, model.getAttribute("savedMission"));
		assertEquals("Création de mission effectuée avec succès.", model.getAttribute("resultTraitement"));
	}

	@Test
	void addMissionSavesEmployeesAndReturnsRecap() {
		var dateDepart = LocalDate.of(2026, 3, 1);
		var dateRetour = LocalDate.of(2026, 3, 5);
		var missions = List.of(new MissionDto());
		when(missionService.saveMissionEmployes("ORD-1", "miss-grpe", "formation", "professionnel",
				dateDepart, dateRetour, "Bangui", "RCA", "motif", "infos", "EMP-1", "EMP-1", "EMP-2"))
				.thenReturn(missions);
		var model = new ExtendedModelMap();

		var view = missionController.addMission("formation", "professionnel", dateDepart, dateRetour,
				"miss-grpe", "Bangui", "RCA", "ORD-1", "motif", "infos", "EMP-1",
				new MockHttpServletRequest(), model, "EMP-1", "EMP-2");

		assertEquals("gmissioncrdeRecap", view);
		assertSame(missions, model.getAttribute("savedMissions"));
		assertEquals("Création de mission effectuée avec succès.", model.getAttribute("resultTraitement"));
	}

	@Test
	void getMissionByNumOrdreAndEmpMatriculeLoadsMissionAndEmployees() throws Exception {
		var mission = new MissionDto();
		var employes = List.of(new EmployeDto());
		when(missionService.getMissionByNumOrdreMissionAndMatriculeEmp("ORD-1", "EMP-1"))
				.thenReturn(Optional.of(mission));
		when(employeService.getAllEmploye()).thenReturn(employes);
		var model = new ExtendedModelMap();

		var view = missionController.getMissionByNumOrdreAndEmpMatricule("ORD-1", "EMP-1",
				new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdeMaj", view);
		assertSame(mission, model.getAttribute("savedMission"));
		assertSame(employes, model.getAttribute("allEmployes"));
	}

	@Test
	void getMissionsByEmpMatriculeAddsHistoryChoice() {
		var missions = List.of(new MissionDto());
		when(missionService.getMissionByEmployeMatricule("EMP-1")).thenReturn(missions);
		var model = new ExtendedModelMap();

		var view = missionController.getMissionsByEmpMatricule("EMP-1", "a-venir", new MockHttpServletRequest(), model);

		assertEquals("gmissionsemphistoriq", view);
		assertSame(missions, model.getAttribute("savedMissionsEmploye"));
		assertEquals("EMP-1", model.getAttribute("missEmpMatricule"));
		assertEquals("a-venir", model.getAttribute("savedChoixStr"));
	}

	@Test
	void getMissionByNumOrdreLoadsEmptyMissionWhenNotFound() throws Exception {
		when(missionService.getMissionByNum("ORD-404")).thenReturn(Optional.empty());
		when(employeService.getAllEmploye()).thenReturn(List.of());
		var model = new ExtendedModelMap();

		var view = missionController.getMissionByNumOrdre("ORD-404", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdeMaj", view);
		assertNotNull(model.getAttribute("savedMission"));
		assertEquals(List.of(), model.getAttribute("allEmployes"));
	}

	@Test
	void deleteMissionRedirectsToMissionList() {
		var view = missionController.deleteMissionByNumOrdr("ORD-1", new MockHttpServletRequest(), new ExtendedModelMap());

		assertEquals("redirect:/missions-emp-crde.html", view);
	}

	private void stubMissionFormData() throws Exception {
		when(missionService.getAllMissions()).thenReturn(List.of());
		when(employeService.getAllEmploye()).thenReturn(List.of());
	}
}