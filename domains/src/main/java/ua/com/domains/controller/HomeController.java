package ua.com.domains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String loginprocesing() {
        return "home";
    }

    @RequestMapping("/loginpage")
    public String login() {
        return "loginpage";
    }


}
