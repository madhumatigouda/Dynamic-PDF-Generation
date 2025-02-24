package com.example.pdfgeneration.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.pdfgeneration.exception.DirectoryNotFoundException;
import com.example.pdfgeneration.model.Invoice;
import com.example.pdfgeneration.service.InvoiceService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
	
	@Value("${invoice.file.path}")
    private String filePath;


    private final InvoiceService invoiceService;
    private final SpringTemplateEngine templateEngine;

    public InvoiceController(InvoiceService invoiceService, SpringTemplateEngine templateEngine) {
        this.invoiceService = invoiceService;
        this.templateEngine = templateEngine;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateInvoice(@RequestBody Invoice invoice, HttpServletResponse response) {
//        String filePath = "C:/Users/DELL/OneDrive/Desktop/Assessment/invoice.pdf";
        try {
            // Create a Thymeleaf context
            Context context = new Context();
            context.setVariable("seller", invoice.getSeller());
            context.setVariable("sellerAddress", invoice.getSellerAddress());
            context.setVariable("sellerGstin", invoice.getSellerGstin());
            context.setVariable("buyer", invoice.getBuyer());
            context.setVariable("buyerAddress", invoice.getBuyerAddress());
            context.setVariable("buyerGstin", invoice.getBuyerGstin());
            context.setVariable("items", invoice.getItems());

            // Generate HTML content from the template
            String htmlContent = templateEngine.process("invoice", context);

            // Generate invoice PDF
            invoiceService.generatePdfFromHtml(htmlContent, filePath);

            // Set response headers and copy the file
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf");
            Files.copy(new File(filePath).toPath(), response.getOutputStream());
            response.getOutputStream().flush();

            return ResponseEntity.ok().build(); // Return a success response
        } catch (DirectoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Directory not found: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating invoice: " + e.getMessage());
        } 
    }


}
