package com.mayank.SpringAi;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PdfController {

    private final ChatClient chatClient;

    public PdfController(ChatClient.Builder builder){
        this.chatClient=builder.build();
    }

    @PostMapping("/ask-pdf")
    public String askPdf(@RequestParam("file")MultipartFile file, @RequestParam("question") String question) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        String prompt = """
                 Answer the queestion using the following document.
                Document:%s
                Question:
                %s
                """.formatted(text, question);
        return chatClient.prompt(prompt).call().content();
    }
}
