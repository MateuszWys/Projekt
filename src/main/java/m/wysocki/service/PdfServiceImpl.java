package m.wysocki.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import m.wysocki.domain.Register;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfServiceImpl implements PdfService {

    public void generatePdf(Register register, HttpServletResponse response) {
        try {
            OutputStream o = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + register.getLogin() + ".pdf");
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, o);
            pdf.open();
            pdf.add(new Paragraph("Pdf example - KAPJ"));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            PdfPTable table = new PdfPTable(2);
            table.addCell("FirsName");
            table.addCell(register.getFirstName());
            table.addCell("LastName");
            table.addCell(register.getLastName());
            table.addCell("Login");
            table.addCell(register.getLogin());
            table.addCell("Email");
            table.addCell(register.getEmail());
            pdf.add(table);
            pdf.close();
            o.close();
        }catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}


