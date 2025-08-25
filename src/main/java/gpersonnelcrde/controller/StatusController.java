package gpersonnelcrde.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gpersonnelcrde.domain.dto.StatusDto;
import gpersonnelcrde.service.StatusService;

@Controller
public class StatusController {
	private StatusService statusService;

	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("/status.html")
	public String getAllStatus(Model model){
		model.addAttribute("allStatus", statusService.getAllStatus());

		return "index.html";
	}
}
