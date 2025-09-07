package gpersonnelcrde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PlanifVisiteController {

	@GetMapping ("/planif-visites-crde.html")
	public String getEmployes(HttpServletRequest request, Model model){

		return "planifvisitecrde";
	}

}
