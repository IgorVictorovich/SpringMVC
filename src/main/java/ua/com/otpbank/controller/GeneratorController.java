package ua.com.otpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.otpbank.service.Generator;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

@Controller
@RequestMapping("/coffee")
public class GeneratorController {
	public GeneratorController(){};

	//String version = getClass().getPackage().getImplementationVersion();

	@Autowired
	private Generator generator = Generator.getInstance();

	int counter;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		counter=1; // rollback counter on init

		model.addAttribute("message", "Participants:");
		model.addAttribute("initList",generator.getCoffeeLovers());
		model.addAttribute("counter",counter++);
		model.addAttribute("timestamp",new Timestamp(Calendar.getInstance().getTime().getTime()).toString());
		model.addAttribute("version",getVersion());
		return "index";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(ModelMap model) {
		model.addAttribute("message", "Participants:");
		model.addAttribute("initList", generator.getCoffeeLovers());
		model.addAttribute("counter",counter++);
		model.addAttribute("timestamp",new Timestamp(Calendar.getInstance().getTime().getTime()).toString());
		model.addAttribute("version",getVersion());
		return "index";
	}

	public synchronized String getVersion() {
		String version = null;

		// try to load from maven properties first
		try {
			Properties p = new Properties();
			InputStream is = getClass().getResourceAsStream("/META-INF/maven/ua.com.otpbank/SpringMVC/pom.properties");
			if (is != null) {
				p.load(is);
				version = p.getProperty("version", "");
			}
		} catch (Exception e) {
			// ignore
		}

		// fallback to using Java API
		if (version == null) {
			Package aPackage = getClass().getPackage();
			if (aPackage != null) {
				version = aPackage.getImplementationVersion();
				if (version == null) {
					version = aPackage.getSpecificationVersion();
				}
			}
		}

		if (version == null) {
			// we could not compute the version so use a blank
			version = "";
		}

		return version;
	}


}