package Pans.Api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Random;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/sendCode")
    public String sendRandomCodeEmail(@RequestParam(name = "email", required = true) String email) throws MessagingException {
        String randomCode = generateRandomCode();
        String recipientEmail = email;

        // Wysyłamy kod w formie HTML w wiadomości email
        sendEmail(recipientEmail, "Kod weryfikacyjny", randomCode);

        return randomCode;
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Losowy 6-cyfrowy kod
        return String.format("%06d", code);
    }

    private void sendEmail(String recipientEmail, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(new InternetAddress(recipientEmail));
        helper.setSubject(subject);

        // Treść wiadomości w formie HTML
        String htmlContent = "<html><body>";
        htmlContent += "<h1>Twój kod weryfikacyjny</h1>";
        htmlContent += "<p>Oto Twój 6-cyfrowy kod weryfikacyjny: <strong>" + message + "</strong></p>";
        htmlContent += "<p>Dziękujemy za korzystanie z naszej usługi.</p>";
        htmlContent += "</body></html>";

        helper.setText(htmlContent, true); // Ustawiamy treść jako HTML

        emailSender.send(mimeMessage);
    }
}

