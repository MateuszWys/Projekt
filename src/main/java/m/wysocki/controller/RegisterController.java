package m.wysocki.controller;

import m.wysocki.domain.VerificationToken;
import m.wysocki.service.MailService;
import m.wysocki.service.ReCaptchaService;
import m.wysocki.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import m.wysocki.domain.Register;
import m.wysocki.service.RegisterService;
import m.wysocki.validator.RegisterValidator;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Controller
public class RegisterController {

    private RegisterValidator registerValidator = new RegisterValidator();
    private RegisterService registerService;
    private ReCaptchaService reCaptchaService;
    private VerificationTokenService verificationTokenService;
    private MailService mailService;

    @Autowired
    public RegisterController(RegisterService registerService, ReCaptchaService reCaptchaService, VerificationTokenService verificationTokenService, MailService mailService) {
        this.registerService = registerService;
        this.reCaptchaService = reCaptchaService;
        this.verificationTokenService = verificationTokenService;
        this.mailService=mailService;
    }

    @RequestMapping(value = "/registers")
    public String showRegisters(Model model, HttpServletRequest request) {

        int registerId = ServletRequestUtils.getIntParameter(request, "registerId" , -1);

        if (registerId > 0)
            model.addAttribute("register", registerService.getRegister(registerId));
        else
            model.addAttribute("register", new Register());

        model.addAttribute("registerList", registerService.listRegister());

        return "register";
    }

    @RequestMapping(value = "/addRegister", method = RequestMethod.POST)
    @Transactional
    public String addRegister(@Valid @ModelAttribute("register") Register register, BindingResult result, Model model, HttpServletRequest request) {

        System.out.println("First Name: " + register.getFirstName() +
                " Last Name: " + register.getLastName() + " Tel.: " +
                register.getTelephone() + " Email: " + register.getEmail()+
                "Login: " +register.getLogin()+ "Password: " + register.getPassword());

        registerValidator.validate(register, result);
        register.setTicket(new HashSet<>(0));

        if (result.getErrorCount() == 0 && reCaptchaService.verify(request.getParameter("g-recaptcha-response"))) {
            if (register.getId()==0){
                VerificationToken verificationToken = new VerificationToken(register);
                verificationTokenService.addToken(verificationToken);
                registerService.addRegister(register);
                String recipient = register.getEmail();
                String subject = "Complete Registration";
                String content = "To confirm your account, please click here : "
                        + "http://localhost:8080/confirmAccount?token="
                        + verificationToken.getVerificationToken();
                mailService.sendMail(recipient, subject, content);
            }
            else
                registerService.editRegister(register);
            return "redirect:registers.html";
        }

        model.addAttribute("registerList", registerService.listRegister());
        return "register";
    }
    @RequestMapping(value = "/confirmAccount", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findByVerificationToken(verificationToken);

        if (token != null) {
            Register register = registerService.findByLogin(token.getRegister().getLogin());
            register.setActive(true);
            registerService.confirmUser(register);
            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping("/delete/{registerId}")
    public String deleteUser(@PathVariable("registerId") Long registerId) {
        registerService.removeRegister(registerId);
        return "redirect:/listofusers.html";
    }

}

