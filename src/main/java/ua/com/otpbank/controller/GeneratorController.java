package ua.com.otpbank.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.otpbank.service.Generator;
import ua.com.otpbank.service.ParticipantsService;
import ua.com.otpbank.tools.Counter;
import ua.com.otpbank.tools.LTimestamp;
import ua.com.otpbank.tools.Version;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/coffee")
public class GeneratorController {
	public GeneratorController() {}

	protected static Logger logger = Logger.getLogger(GeneratorController.class);


	@Autowired
	private HttpServletRequest request;

	@Autowired
	private Generator generator = Generator.getInstance();

	private Counter counter = Counter.getInstance();

	private LTimestamp local_timestamp = LTimestamp.getInstance();

	@Resource(name = "participantsService")
	ParticipantsService participantsService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model) {
		counter.setCounter(1); // set counter on init

		model.addAttribute("message", "Participants:");
		model.addAttribute("initList", generator.getCoffeeLovers());
		model.addAttribute("counter", counter.getCounter());
		model.addAttribute("timestamp", local_timestamp.getNextTimestamp());
		model.addAttribute("version", new Version().getVersion());
		model.addAttribute("generatedMethod", generator.getGeneratorMethod());
		model.addAttribute("headOfList", participantsService.getHeadOfList());

		//persist generated data
		if(generator.getGeneratorMethod().equals("statistics-based-shuffle")){
			model.addAttribute("statusString", participantsService.persistParticipants(generator.getShuffledCoffeeLovers()));
		}
		counter.setNext(); //iterate counter
		return "index";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(ModelMap model) {

		model.addAttribute("message", "Participants:");
		model.addAttribute("initList", generator.getCoffeeLovers());
		model.addAttribute("counter", counter.getCounter());
		model.addAttribute("timestamp", local_timestamp.getNextTimestamp());
		model.addAttribute("version",  new Version().getVersion());
		model.addAttribute("generatedMethod", generator.getGeneratorMethod());
		model.addAttribute("headOfList", participantsService.getHeadOfList());

		//persist generated data
		if(generator.getGeneratorMethod().equals("statistics-based-shuffle")){
			model.addAttribute("statusString", participantsService.persistParticipants(generator.getShuffledCoffeeLovers()));
		}
		counter.setNext(); //iterate counter
		return "index";

	}

}