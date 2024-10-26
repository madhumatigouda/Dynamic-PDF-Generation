# Dynamic PDF Generation

## Overview

This project is a Spring Boot application that provides a REST API to generate PDFs using a Java template engine. It accepts data in a specified JSON format and generates a PDF document based on the received data. The generated PDFs are stored locally, allowing for quick retrieval on subsequent requests with the same data.

## Features

- REST API to accept data and generate a PDF based on the received data.
- Ability to download the generated PDF.
- Storage of generated PDFs on local storage for quick retrieval.
- Uses Java Template Engines like Thymeleaf or iText for PDF generation.
- Input schema is well-defined to ensure proper data handling.

## API Specification

### Generate PDF

- **Endpoint:** `POST /api/invoice/generate`
- **Description:** Generates a PDF based on the provided data and stores it locally.
- **Request Body:**

```json
{
  "seller": "XYZ Pvt. Ltd.",
  "sellerGstin": "29AABBCCDD121ZD",
  "sellerAddress": "New Delhi, India",
  "buyer": "Vedant Computers",
  "buyerGstin": "29AABBCCDD131ZD",
  "buyerAddress": "New Delhi, India",
  "items": [
    {
      "name": "Product 1",
      "quantity": "12 Nos",
      "rate": 123.00,
      "amount": 1476.00
    }
  ]
}
```
## Update
- Change the invoice file path in the application.property file path where you want to store the invoice files.
## Postman
![image](https://github.com/user-attachments/assets/24809161-babf-4e83-b66b-51689dd612ec)



