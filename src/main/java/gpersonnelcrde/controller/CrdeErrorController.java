package gpersonnelcrde.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CrdeErrorController implements ErrorController {
	private final static Logger logger = LoggerFactory.getLogger(CrdeErrorController.class);

	@GetMapping("/error")
	 public String handleError(HttpServletRequest request, Model model) {
		model.addAttribute("ERROR_STATUS_CODE", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		model.addAttribute("ERROR_MESSAGE", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
		model.addAttribute("ERROR_EXCEPTION", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        logger.warn("Une ou plusieurs erreurs sont survenues, {}", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        return "error";
    }
}
