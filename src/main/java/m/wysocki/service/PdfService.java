package m.wysocki.service;

import m.wysocki.domain.Register;
import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    public void generatePdf(Register register, HttpServletResponse response);
}
