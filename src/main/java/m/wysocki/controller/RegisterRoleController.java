package m.wysocki.controller;

import m.wysocki.domain.RegisterRole;
import m.wysocki.service.RegisterRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class RegisterRoleController {
    private RegisterRoleService registerRoleService;

    @Autowired
    public RegisterRoleController(RegisterRoleService registerRoleService) {
        this.registerRoleService = registerRoleService;
    }

    @RequestMapping(value="/registerRole")
    public String showUserRole(Model model) {
        model.addAttribute("registerRole", new RegisterRole());
        return "registerRole";
    }

    @RequestMapping(value = "/addRegisterRole", method = RequestMethod.POST)
    public String addUserRole(@ModelAttribute("registerRole") RegisterRole registerRole) {
        registerRoleService.addRegisterRole(registerRole);
        return "redirect:/registerRole.html";
    }
}
