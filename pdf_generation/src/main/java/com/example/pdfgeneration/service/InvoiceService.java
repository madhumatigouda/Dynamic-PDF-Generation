package com.example.pdfgeneration.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.pdfgeneration.exception.DirectoryNotFoundException;
import com.example.pdfgeneration.exception.InvoiceGenerationException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final SpringTemplateEngine templateEngine;

    public InvoiceService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void generatePdfFromHtml(String htmlContent, String filePath) throws InvoiceGenerationException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir == null || !parentDir.exists()) {
                logger.error("Directory does not exist: {}", (parentDir != null ? parentDir.getAbsolutePath() : "No parent directory found"));
                if (parentDir != null && parentDir.mkdirs()) {
                    logger.info("Created directory: {}", parentDir.getAbsolutePath());
                } else {
                    throw new DirectoryNotFoundException("Failed to create directory: " + (parentDir != null ? parentDir.getAbsolutePath() : "No parent directory found"));
                }
            }

            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(outputStream);
            }
        } catch (DirectoryNotFoundException e) {
            logger.error("Error generating PDF: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error generating PDF from HTML: {}", e.getMessage(), e);
            throw new InvoiceGenerationException("Error generating PDF from HTML", e);
        }
    }
}
