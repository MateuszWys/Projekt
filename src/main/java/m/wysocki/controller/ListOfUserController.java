package m.wysocki.controller;


import m.wysocki.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ListOfUserController {

    private RegisterService registerService;

    public ListOfUserController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/listofusers")
    public String listOfUsers(Model model, HttpServletRequest request) {
        model.addAttribute("registerList", registerService.listRegister());
        return "listOfUsers";
    }
}


