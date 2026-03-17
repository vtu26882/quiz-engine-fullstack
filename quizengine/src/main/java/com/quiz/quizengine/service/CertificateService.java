package com.quiz.quizengine.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class CertificateService {

    public File generateCertificate(String username, String quizTitle, int score, int total) {

        try {

            String fileName = "certificate_" + username + ".pdf";
            File file = new File(fileName);

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 28, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 16);
            Font nameFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD);

            Paragraph title = new Paragraph("Certificate of Completion", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            Paragraph line1 = new Paragraph("This certificate is proudly presented to", normalFont);
            line1.setAlignment(Element.ALIGN_CENTER);
            document.add(line1);

            document.add(new Paragraph("\n"));

            Paragraph name = new Paragraph(username.toUpperCase(), nameFont);
            name.setAlignment(Element.ALIGN_CENTER);
            document.add(name);

            document.add(new Paragraph("\n"));

            Paragraph line2 = new Paragraph("For successfully completing the quiz", normalFont);
            line2.setAlignment(Element.ALIGN_CENTER);
            document.add(line2);

            Paragraph quiz = new Paragraph(quizTitle, nameFont);
            quiz.setAlignment(Element.ALIGN_CENTER);
            document.add(quiz);

            document.add(new Paragraph("\n"));

            Paragraph scoreText = new Paragraph("Score: " + score + " / " + total, normalFont);
            scoreText.setAlignment(Element.ALIGN_CENTER);
            document.add(scoreText);

            document.add(new Paragraph("\n"));

            Paragraph date = new Paragraph("Date: " + LocalDate.now(), normalFont);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            document.add(new Paragraph("\n\n"));

            Paragraph sign = new Paragraph("Authorized Signature", normalFont);
            sign.setAlignment(Element.ALIGN_RIGHT);
            document.add(sign);

            document.close();

            return file;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}