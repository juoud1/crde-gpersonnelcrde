package gpersonnelcrde.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gpersonnelcrde.service.CongeService;
import gpersonnelcrde.service.MissionService;

class RepriseTravailSuiteMissionCongeControllerTest {
	private MissionService missionService;
	private CongeService congeService;
	private RepriseTravailSuiteMissionCongeController controller;

	@BeforeEach
	void setUp() {
		missionService = mock(MissionService.class);
		congeService = mock(CongeService.class);
		controller = new RepriseTravailSuiteMissionCongeController(missionService, congeService);
	}

	@Test
	void processRepriseUpdatesMissions() throws Exception {
		var dateDebut = LocalDate.of(2026, 1, 1);
		var dateFin = LocalDate.of(2026, 1, 31);

		var view = controller.processReprise("mission", dateDebut, dateFin);

		assertEquals("redirect:/employes-crde.html", view);
		verify(missionService).updateMissionsEffectuees(dateDebut, dateFin);
		verifyNoInteractions(congeService);
	}

	@Test
	void processRepriseUpdatesConges() throws Exception {
		var dateDebut = LocalDate.of(2026, 2, 1);
		var dateFin = LocalDate.of(2026, 2, 28);

		var view = controller.processReprise("conge", dateDebut, dateFin);

		assertEquals("redirect:/employes-crde.html", view);
		verify(congeService).updateCongesEffectuees(dateDebut, dateFin);
		verifyNoInteractions(missionService);
	}

	@Test
	void processRepriseRejectsUnknownType() {
		assertThrows(IllegalArgumentException.class, () -> controller.processReprise("autre", null, null));
		verifyNoInteractions(missionService, congeService);
	}

	@Test
	void processRepriseRejectsBlankType() {
		assertThrows(IllegalArgumentException.class, () -> controller.processReprise(" ", null, null));
		verifyNoInteractions(missionService, congeService);
	}
}