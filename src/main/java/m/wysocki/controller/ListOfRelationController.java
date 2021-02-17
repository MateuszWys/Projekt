package m.wysocki.controller;

import m.wysocki.domain.Register;
import m.wysocki.service.RegisterService;
import m.wysocki.service.RelationTimeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.relation.RelationService;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;


@Controller
public class ListOfRelationController {

    private final RegisterService registerService;
    private final RelationTimeService relationTimeService;


    public ListOfRelationController(RegisterService registerService, RelationTimeService relationTimeService) {
        this.registerService = registerService;
        this.relationTimeService = relationTimeService;
    }
    
    @RequestMapping(value = "/exampleTwo")
    public String exampleTwo(Authentication authentication, Model model) {
        for (GrantedAuthority a:
             authentication.getAuthorities()) {
            System.out.println(a.getAuthority());
            if(a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MANAGER")){
                model.addAttribute("registerList", registerService.listRegister());
                return "exampleTwo";
            }
        }
        model.addAttribute("registerList", new ArrayList<>(Arrays.asList(new Register[]{registerService.findByLogin(authentication.getName())})));
        return "exampleTwo";
    }

    @RequestMapping("/deleteTicket")
    public String deleteTicket(Model model, HttpServletRequest request) {
        long ticketId = ServletRequestUtils.getLongParameter(request, "ticketId" , -1);
        System.out.println(ticketId);
        relationTimeService.removeTicket(ticketId);
        return "redirect:/exampleTwo.html";
    }
}
