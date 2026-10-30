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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.MissionEmployeDto;
import gpersonnelcrde.domain.dto.MissionEmployesDto;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.MissionEmployeService;

class MissionEmployeControllerTest {
	private MissionEmployeService missionEmployeService;
	private EmployeService employeService;
	private MissionEmployeController controller;

	@BeforeEach
	void setUp() {
		missionEmployeService = mock(MissionEmployeService.class);
		employeService = mock(EmployeService.class);
		controller = new MissionEmployeController(missionEmployeService, employeService);
	}

	@Test
	void getGestMissionsEmployesSortsMissionsAndFiltersEmployees() throws Exception {
		var missionDeux = missionEmployes("2", "miss-grpe");
		var missionUn = missionEmployes("1", "miss-grpe");
		var actif = employe("ACT");
		var archive = employe("AUT");
		when(missionEmployeService.getAllMissionEmployes()).thenReturn(List.of(missionDeux, missionUn));
		when(employeService.getAllEmploye()).thenReturn(List.of(actif, archive));
		var model = new ExtendedModelMap();

		var view = controller.getGestMissionsEmployes(new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdelist", view);
		assertEquals(List.of(missionUn, missionDeux), model.getAttribute("allMissionsEmployes"));
		assertEquals(List.of(actif), model.getAttribute("employesEnSvce"));
	}

	@Test
	void getMissionEmployeForCreationSetsGroupType() throws Exception {
		var missionDeux = missionEmployes("2", "miss-grpe");
		var missionUn = missionEmployes("1", "miss-grpe");
		var actif = employe("ACT");
		var archive = employe("AUT");
		when(missionEmployeService.getAllMissionEmployes()).thenReturn(List.of(missionDeux, missionUn));
		when(employeService.getAllEmploye()).thenReturn(List.of(actif, archive));
		var model = new ExtendedModelMap();

		var view = controller.getMissionEmployeForCreation("miss-grpe", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrde", view);
		assertEquals(List.of(missionUn, missionDeux), model.getAttribute("allMissionsEmployes"));
		assertEquals(List.of(actif), model.getAttribute("employesEnSvce"));
		assertEquals("Mission de groupe", model.getAttribute("typMissValue"));
		assertEquals("", model.getAttribute("paysResidenceText"));
	}

	@Test
	void getMissionEmployeForCreationSetsIndividualType() throws Exception {
		when(missionEmployeService.getAllMissionEmployes()).thenReturn(List.of());
		when(employeService.getAllEmploye()).thenReturn(List.of());
		var model = new ExtendedModelMap();

		var view = controller.getMissionEmployeForCreation("individuelle", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrde", view);
		assertEquals("Mission", model.getAttribute("typMissValue"));
		assertEquals("Rép. Centrafricaine", model.getAttribute("paysResidenceText"));
	}

	@Test
	void addMissionEmplAddsSavedMissionAndGroupLabels() throws Exception {
		var savedMission = missionEmployes("15", "miss-grpe");
		var allMissions = List.of(savedMission);
		when(missionEmployeService.saveMissionEmployes("ORD-1", "miss-grpe", "formation", "professionnel",
				null, null, "cinq jours", "Bangui", "RCA", "motif", "infos", "EMP-1", "EMP-1", "EMP-2"))
				.thenReturn(savedMission);
		when(missionEmployeService.getAllMissionEmployes()).thenReturn(allMissions);
		var model = new ExtendedModelMap();

		var view = controller.addMissionEmpl("formation", "professionnel", null, null, "miss-grpe",
				"Bangui", "RCA", "ORD-1", "cinq jours", "motif", "infos", "EMP-1",
				new MockHttpServletRequest(), model, "EMP-1", "EMP-2");

		assertEquals("gmissioncrdeRecap", view);
		assertSame(savedMission, model.getAttribute("savedMissionEmployes"));
		assertEquals(allMissions, model.getAttribute("allMissionsEmployes"));
		assertEquals("création de nouvelle mission", model.getAttribute("traitement"));
		assertEquals("Mission de groupe", model.getAttribute("typMissText"));
	}

	@Test
	void getMissionByNumAndEmpMatriculeLoadsMissionAndGroupLabels() throws Exception {
		var mission = new MissionEmployeDto();
		mission.setTypeOrdreMission("miss-grpe");
		var employes = List.of(new EmployeDto());
		when(missionEmployeService.getMissionEmployeByNumMissAndMatriculeEmp("15", "EMP-1"))
				.thenReturn(Optional.of(mission));
		when(employeService.getAllEmploye()).thenReturn(employes);
		var model = new ExtendedModelMap();

		var view = controller.getMissionByNumAndEmpMatricule("15", "EMP-1", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdeMaj", view);
		assertSame(mission, model.getAttribute("savedMissionEmploye"));
		assertSame(employes, model.getAttribute("allEmployes"));
		assertEquals("Mission de groupe", model.getAttribute("typMissValue"));
	}

	@Test
	void getMissionsByEmpMatriculeAddsChoiceWhenNotHistory() throws Exception {
		var mission = new MissionEmployeDto();
		mission.setNumMission("15");
		var missions = List.of(mission);
		when(missionEmployeService.getMissionsEmployesByMatriculeEmp("EMP-1")).thenReturn(missions);
		var model = new ExtendedModelMap();

		var view = controller.getMissionsByEmpMatricule("EMP-1", "a-venir", new MockHttpServletRequest(), model);

		assertEquals("gmissionsemphistoriq", view);
		assertSame(missions, model.getAttribute("savedMissionsEmployes"));
		assertEquals("EMP-1", model.getAttribute("missEmpMatricule"));
		assertEquals("a-venir", model.getAttribute("savedChoixStr"));
	}

	@Test
	void getMissionsByEmpMatriculeOmitsHistoryChoice() throws Exception {
		when(missionEmployeService.getMissionsEmployesByMatriculeEmp("EMP-1")).thenReturn(List.of());
		var model = new ExtendedModelMap();

		controller.getMissionsByEmpMatricule("EMP-1", "hist", new MockHttpServletRequest(), model);

		assertEquals("EMP-1", model.getAttribute("missEmpMatricule"));
		assertEquals(null, model.getAttribute("savedChoixStr"));
	}

	@Test
	void getMissionByNumLoadsMissionListAndEmployees() throws Exception {
		var missionUn = missionEmployes("1", "miss-grpe");
		var missionDeux = missionEmployes("2", "individuelle");
		var savedMission = missionEmployes("2", "individuelle");
		var employes = List.of(employe("ACT"), employe("AUT"));
		when(missionEmployeService.getMissionEmployesByNumMiss("2")).thenReturn(Optional.of(savedMission));
		when(missionEmployeService.getAllMissionEmployes()).thenReturn(List.of(missionDeux, missionUn));
		when(employeService.getAllEmploye()).thenReturn(employes);
		var model = new ExtendedModelMap();

		var view = controller.getMissionByNum("2", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdeMaj", view);
		assertSame(savedMission, model.getAttribute("savedMissionEmployes"));
		assertEquals(List.of(missionUn, missionDeux), model.getAttribute("allMissionsEmployes"));
		assertEquals(employes, model.getAttribute("allEmployes"));
		assertEquals(List.of(employes.get(0)), model.getAttribute("employesEnSvce"));
		assertEquals("Mission", model.getAttribute("typMissValue"));
	}

	@Test
	void getMissionByNumUsesEmptyDtoWhenMissionIsMissing() throws Exception {
		when(missionEmployeService.getMissionEmployesByNumMiss("404")).thenReturn(Optional.empty());
		when(missionEmployeService.getAllMissionEmployes()).thenReturn(List.of());
		when(employeService.getAllEmploye()).thenReturn(List.of());
		var model = new ExtendedModelMap();

		var view = controller.getMissionByNum("404", new MockHttpServletRequest(), model);

		assertEquals("gmissioncrdeMaj", view);
		assertNotNull(model.getAttribute("savedMissionEmployes"));
		assertEquals(List.of(), model.getAttribute("allMissionsEmployes"));
	}

	private MissionEmployesDto missionEmployes(String numMission, String typeOrdreMission) {
		var mission = new MissionEmployesDto();
		mission.setNumMission(numMission);
		mission.setTypeOrdreMission(typeOrdreMission);
		return mission;
	}

	private EmployeDto employe(String status) {
		var employe = new EmployeDto();
		employe.setStatus(status);
		return employe;
	}
}