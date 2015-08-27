package ua.com.otpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.otpbank.service.Generator;

/**
 * Created by Igor on 04.05.2015.
 */
@Controller
@RequestMapping("/send_result")
public class MailController {
    @Autowired
    private Generator generator = Generator.getInstance();
 /*   @Autowired
    private SendMailService mailService = new SendMailService();*/

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model){

        model.addAttribute("shuffledList", generator.getShuffledCoffeeLovers());
      //  model.addAttribute("getList", generator.getCoffeeLovers());
/*        mailService.composeNewMail();*/
        return "send_result";
    }
}
