package ua.com.otpbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Igor on 04.05.2015.
 */
@Controller
@RequestMapping("/send_result")
public class MailController {
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model){
        return "send_result";
    }
}
