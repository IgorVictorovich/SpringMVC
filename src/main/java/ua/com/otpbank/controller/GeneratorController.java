package ua.com.otpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.otpbank.domain.ParticipantsService;
import ua.com.otpbank.service.Generator;
import ua.com.otpbank.tools.Version;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;

@Controller
@RequestMapping("/coffee")
public class GeneratorController {
	public GeneratorController() {
	}

	private Version version = new Version();

	private HttpServletRequest request;

	@Autowired
	private Generator generator = Generator.getInstance();
	//debug
	ParticipantsService participantsService = new ParticipantsService();

	int counter;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		counter = 1; // rollback counter on init

		model.addAttribute("message", "Participants:");
		model.addAttribute("initList", generator.getCoffeeLovers());
		model.addAttribute("counter", counter++);
		model.addAttribute("timestamp", new Timestamp(Calendar.getInstance().getTime().getTime()).toString());
		model.addAttribute("version", version.getVersion());
		model.addAttribute("generatedMethod", generator.getGeneratorMethod());
		//debug
		model.addAttribute("head", participantsService.getHeadOfList());

		return "index";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(ModelMap model) {

		if (request.getParameter("generate") != null) {
			model.addAttribute("message", "Participants:");
			model.addAttribute("initList", generator.getCoffeeLovers());
			model.addAttribute("counter", counter++);
			model.addAttribute("timestamp", new Timestamp(Calendar.getInstance().getTime().getTime()).toString());
			model.addAttribute("version", version.getVersion());
			model.addAttribute("generatedMethod", generator.getGeneratorMethod());
			return "index";
		}
		else
		if (request.getParameter("persist") != null){
			model.addAttribute("message", "Participants:");
			model.addAttribute("initList", generator.getShuffledCoffeeLovers());
			model.addAttribute("counter", counter);
			//model.addAttribute("timestamp", new Timestamp(Calendar.getInstance().getTime().getTime()).toString());
			model.addAttribute("version", version.getVersion());
			model.addAttribute("generatedMethod", generator.getGeneratorMethod());
			return "index";
		}

		return "index";
	}

}