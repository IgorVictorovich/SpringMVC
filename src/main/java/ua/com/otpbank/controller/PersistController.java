package ua.com.otpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.otpbank.service.Generator;
import ua.com.otpbank.service.ParticipantsService;

import javax.annotation.Resource;

/**
 * Created by Igor on 11.05.2015.
 */
@Controller
@RequestMapping("/persist_data")
public class PersistController {
    @Autowired
    private Generator generator = Generator.getInstance();

    @Resource(name = "participantsService")
    ParticipantsService participantsService;

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model){

        model.addAttribute("statusString", participantsService.persistParticipants(generator.getShuffledCoffeeLovers()));

        return "persist_data";
    }
}
