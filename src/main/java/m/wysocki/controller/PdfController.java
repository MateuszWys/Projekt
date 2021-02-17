package m.wysocki.controller;

import m.wysocki.service.PdfService;
import m.wysocki.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PdfController {

    private PdfService pdfService;
    private RegisterService registerService;

    @Autowired
    public PdfController(PdfService pdfService, RegisterService registerService) {
        this.pdfService = pdfService;
        this.registerService = registerService;
    }

    @RequestMapping(value = "/generatePdf-{registerId}", method = RequestMethod.GET)
    public void generatePdf(@PathVariable Integer registerId, HttpServletResponse response) {
        pdfService.generatePdf(registerService.getRegister(registerId), response);
    }
}


