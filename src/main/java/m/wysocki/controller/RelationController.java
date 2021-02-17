package m.wysocki.controller;


import m.wysocki.domain.Register;
import m.wysocki.domain.Relation;
import m.wysocki.domain.RelationTime;
import m.wysocki.dto.RelationDTO;
import m.wysocki.service.MyRegisterDetailsService;
import m.wysocki.service.RegisterService;
import m.wysocki.service.RegisterServiceImpl;
import m.wysocki.service.RelationTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
public class RelationController {
    private RelationTimeService relationTimeService;
    private RegisterService registerService;
    UserDetailsService userDetailsService;

    @Autowired
    public RelationController(RelationTimeService relationTimeService, UserDetailsService userDetailsService, RegisterService registerService) {
        this.relationTimeService = relationTimeService;
        this.userDetailsService = userDetailsService;
        this.registerService = registerService;
    }

    @RequestMapping(value = "/exampleThree")
    public String exampleThree(Model model, HttpServletRequest request) {
        model.addAttribute("relationList", relationTimeService.getListOfRelationTimes());
        return "exampleThree";
    }

    @RequestMapping(value = "/showRelation")
    public String showRelation(Model model, HttpServletRequest request){
        long relationId = ServletRequestUtils.getLongParameter(request, "relationId" , -1);

        System.out.println(relationId);
        if (relationId > 0)
            model.addAttribute("relationTime", relationTimeService.getRelationTime(relationId));
        else
            model.addAttribute("relationTime", new RelationTime());

        model.addAttribute("relationList", relationTimeService.getListOfRelationTimes());
        return "addRelation";
    }

    @RequestMapping(value = "/buyTicket/{id}")
    public String buyTicket(Authentication authentication, @PathVariable long id){
        registerService.buyTicket(id, authentication.getName());
        return "redirect:/exampleThree.html";
    }

    @RequestMapping(value = "/addRelation", method = RequestMethod.POST)
    public String addRelation(@Valid @ModelAttribute RelationDTO relationTime, BindingResult result) throws ParseException {

        System.out.println("SIEA");
        if(result.getErrorCount() == 0 && relationTime.getId() <= 0){
            RelationTime relationTime1 = new RelationTime();
            relationTime1.setRelation(relationTime.getRelation());
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            relationTime1.setTimeFrom(format.parse(relationTime.getTimeFrom()));
            relationTime1.setTimeTo(format.parse(relationTime.getTimeTo()));
            relationTime1.setId(relationTime.getId());
            relationTimeService.addRelationTime(relationTime1);
        }else if(relationTime.getId() > 0) {

            RelationTime relationTime1 = new RelationTime();
            relationTime1.setRelation(relationTime.getRelation());
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            relationTime1.setTimeFrom(format.parse(relationTime.getTimeFrom()));
            relationTime1.setTimeTo(format.parse(relationTime.getTimeTo()));
            relationTime1.setId(relationTime.getId());
            System.out.println(relationTime.getId());
            relationTimeService.editRelationTime(relationTime1);
        }
        return "redirect:/exampleThree.html";
    }

    @RequestMapping(value = "/deleteRelation")
    public String deleteRelation(Model model, HttpServletRequest request){
        long relationId = ServletRequestUtils.getLongParameter(request, "relationId" , -1);
        if(relationId > 0){
            relationTimeService.deleteById(relationId);
        }
        return "redirect:/exampleThree.html";
    }

}
