package Pans.Api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Random;

@RestController
@RequestMapping("/auth/email")
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
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang='en' xmlns='http://www.w3.org/1999/xhtml' xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office'>\n" +
                "<head>\n" +
                "    <meta charset='utf-8'> <!-- utf-8 works for most cases -->\n" +
                "    <meta name='viewport' content='width=device-width'> <!-- Forcing initial-scale shouldn't be necessary -->\n" +
                "    <meta http-equiv='X-UA-Compatible' content='IE=edge'> <!-- Use the latest (edge) version of IE rendering engine -->\n" +
                "    <meta name='x-apple-disable-message-reformatting'>  <!-- Disable auto-scale in iOS 10 Mail entirely -->\n" +
                "    <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->\n" +
                "\n" +
                "    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet'>\n" +
                "\n" +
                "    <!-- CSS Reset : BEGIN -->\n" +
                "    <style>\n" +
                "\n" +
                "        /* What it does: Remove spaces around the email design added by some email clients. */\n" +
                "        /* Beware: It can remove the padding / margin and add a background color to the compose a reply window. */\n" +
                "        html,\n" +
                "body {\n" +
                "    margin: 0 auto !important;\n" +
                "    padding: 0 !important;\n" +
                "    height: 100% !important;\n" +
                "    width: 50% !important;\n" +
                "    background: #f1f1f1;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Stops email clients resizing small text. */\n" +
                "* {\n" +
                "    -ms-text-size-adjust: 100%;\n" +
                "    -webkit-text-size-adjust: 100%;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Centers email on Android 4.4 */\n" +
                "div[style*='margin: 16px 0'] {\n" +
                "    margin: 0 !important;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Stops Outlook from adding extra spacing to tables. */\n" +
                "table,\n" +
                "td {\n" +
                "    mso-table-lspace: 0pt !important;\n" +
                "    mso-table-rspace: 0pt !important;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Fixes webkit padding issue. */\n" +
                "table {\n" +
                "    border-spacing: 0 !important;\n" +
                "    border-collapse: collapse !important;\n" +
                "    table-layout: fixed !important;\n" +
                "    margin: 0 auto !important;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Uses a better rendering method when resizing images in IE. */\n" +
                "img {\n" +
                "    -ms-interpolation-mode:bicubic;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Prevents Windows 10 Mail from underlining links despite inline CSS. Styles for underlined links should be inline. */\n" +
                "a {\n" +
                "    text-decoration: none;\n" +
                "}\n" +
                "\n" +
                "/* What it does: A work-around for email clients meddling in triggered links. */\n" +
                "*[x-apple-data-detectors],  /* iOS */\n" +
                ".unstyle-auto-detected-links *,\n" +
                ".aBn {\n" +
                "    border-bottom: 0 !important;\n" +
                "    cursor: default !important;\n" +
                "    color: inherit !important;\n" +
                "    text-decoration: none !important;\n" +
                "    font-size: inherit !important;\n" +
                "    font-family: inherit !important;\n" +
                "    font-weight: inherit !important;\n" +
                "    line-height: inherit !important;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Prevents Gmail from displaying a download button on large, non-linked images. */\n" +
                ".a6S {\n" +
                "    display: none !important;\n" +
                "    opacity: 0.01 !important;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Prevents Gmail from changing the text color in conversation threads. */\n" +
                ".im {\n" +
                "    color: inherit !important;\n" +
                "}\n" +
                "\n" +
                "/* If the above doesn't work, add a .g-img class to any image in question. */\n" +
                "img.g-img + div {\n" +
                "    display: none !important;\n" +
                "}\n" +
                "\n" +
                "/* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\n" +
                "/* Create one of these media queries for each additional viewport size you'd like to fix */\n" +
                "\n" +
                "/* iPhone 4, 4S, 5, 5S, 5C, and 5SE */\n" +
                "@media only screen and (min-device-width: 320px) and (max-device-width: 374px) {\n" +
                "    u ~ div .email-container {\n" +
                "        min-width: 320px !important;\n" +
                "    }\n" +
                "}\n" +
                "/* iPhone 6, 6S, 7, 8, and X */\n" +
                "@media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\n" +
                "    u ~ div .email-container {\n" +
                "        min-width: 375px !important;\n" +
                "    }\n" +
                "}\n" +
                "/* iPhone 6+, 7+, and 8+ */\n" +
                "@media only screen and (min-device-width: 414px) {\n" +
                "    u ~ div .email-container {\n" +
                "        min-width: 414px !important;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "    <!-- CSS Reset : END -->\n" +
                "\n" +
                "    <!-- Progressive Enhancements : BEGIN -->\n" +
                "    <style>\n" +
                "\n" +
                "\t    .primary{\n" +
                "\tbackground: #30e3ca;\n" +
                "}\n" +
                ".bg_white{\n" +
                "\tbackground: #ffffff;\n" +
                "}\n" +
                ".bg_light{\n" +
                "\tbackground: #fafafa;\n" +
                "}\n" +
                ".bg_black{\n" +
                "\tbackground: #000000;\n" +
                "}\n" +
                ".bg_dark{\n" +
                "\tbackground: rgba(0,0,0,.8);\n" +
                "}\n" +
                ".email-section{\n" +
                "\tpadding:2.5em;\n" +
                "}\n" +
                "\n" +
                "/*BUTTON*/\n" +
                ".btn{\n" +
                "\tpadding: 10px 15px;\n" +
                "\tdisplay: inline-block;\n" +
                "}\n" +
                ".btn.btn-primary{\n" +
                "\tborder-radius: 5px;\n" +
                "\tbackground: #30e3ca;\n" +
                "\tcolor: #ffffff;\n" +
                "}\n" +
                ".btn.btn-white{\n" +
                "\tborder-radius: 5px;\n" +
                "\tbackground: #ffffff;\n" +
                "\tcolor: #000000;\n" +
                "}\n" +
                ".btn.btn-white-outline{\n" +
                "\tborder-radius: 5px;\n" +
                "\tbackground: transparent;\n" +
                "\tborder: 1px solid #fff;\n" +
                "\tcolor: #fff;\n" +
                "}\n" +
                ".btn.btn-black-outline{\n" +
                "\tborder-radius: 0px;\n" +
                "\tbackground: transparent;\n" +
                "\tborder: 2px solid #000;\n" +
                "\tcolor: #000;\n" +
                "\tfont-weight: 700;\n" +
                "}\n" +
                "\n" +
                "h1,h2,h3,h4,h5,h6{\n" +
                "\tfont-family: 'Lato', sans-serif;\n" +
                "\tcolor: #000000;\n" +
                "\tmargin-top: 0;\n" +
                "\tfont-weight: 400;\n" +
                "}\n" +
                "\n" +
                "body{\n" +
                "\tfont-family: 'Lato', sans-serif;\n" +
                "\tfont-weight: 400;\n" +
                "\tfont-size: 15px;\n" +
                "\tline-height: 1.8;\n" +
                "\tcolor: rgba(0,0,0,.4);\n" +
                "}\n" +
                "\n" +
                "a{\n" +
                "\tcolor: #30e3ca;\n" +
                "}\n" +
                "\n" +
                "table{\n" +
                "}\n" +
                "/*LOGO*/\n" +
                "\n" +
                ".logo h1{\n" +
                "\tmargin: 0;\n" +
                "}\n" +
                ".logo h1 a{\n" +
                "\tcolor: #30e3ca;\n" +
                "\tfont-size: 24px;\n" +
                "\tfont-weight: 700;\n" +
                "\tfont-family: 'Lato', sans-serif;\n" +
                "}\n" +
                "\n" +
                "/*HERO*/\n" +
                ".hero{\n" +
                "\tposition: relative;\n" +
                "\tz-index: 0;\n" +
                "}\n" +
                "\n" +
                ".hero .text{\n" +
                "\tcolor: rgba(0,0,0,.3);\n" +
                "}\n" +
                ".hero .text h2{\n" +
                "\tcolor: #000;\n" +
                "\tfont-size: 40px;\n" +
                "\tmargin-bottom: 0;\n" +
                "\tfont-weight: 400;\n" +
                "\tline-height: 1.4;\n" +
                "}\n" +
                ".hero .text h3{\n" +
                "\tfont-size: 24px;\n" +
                "\tfont-weight: 300;\n" +
                "}\n" +
                ".hero .text h2 span{\n" +
                "\tfont-weight: 600;\n" +
                "\tcolor: #30e3ca;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/*HEADING SECTION*/\n" +
                ".heading-section{\n" +
                "}\n" +
                ".heading-section h2{\n" +
                "\tcolor: #000000;\n" +
                "\tfont-size: 28px;\n" +
                "\tmargin-top: 0;\n" +
                "\tline-height: 1.4;\n" +
                "\tfont-weight: 400;\n" +
                "}\n" +
                ".heading-section .subheading{\n" +
                "\tmargin-bottom: 20px !important;\n" +
                "\tdisplay: inline-block;\n" +
                "\tfont-size: 13px;\n" +
                "\ttext-transform: uppercase;\n" +
                "\tletter-spacing: 2px;\n" +
                "\tcolor: rgba(0,0,0,.4);\n" +
                "\tposition: relative;\n" +
                "}\n" +
                ".heading-section .subheading::after{\n" +
                "\tposition: absolute;\n" +
                "\tleft: 0;\n" +
                "\tright: 0;\n" +
                "\tbottom: -10px;\n" +
                "\tcontent: '';\n" +
                "\twidth: 50%;\n" +
                "\theight: 2px;\n" +
                "\tbackground: #30e3ca;\n" +
                "\tmargin: 0 auto;\n" +
                "}\n" +
                "\n" +
                ".heading-section-white{\n" +
                "\tcolor: rgba(255,255,255,.8);\n" +
                "}\n" +
                ".heading-section-white h2{\n" +
                "\tfont-family: \n" +
                "\tline-height: 1;\n" +
                "\tpadding-bottom: 0;\n" +
                "}\n" +
                ".heading-section-white h2{\n" +
                "\tcolor: #ffffff;\n" +
                "}\n" +
                ".heading-section-white .subheading{\n" +
                "\tmargin-bottom: 0;\n" +
                "\tdisplay: inline-block;\n" +
                "\tfont-size: 13px;\n" +
                "\ttext-transform: uppercase;\n" +
                "\tletter-spacing: 2px;\n" +
                "\tcolor: rgba(255,255,255,.4);\n" +
                "}\n" +
                "\n" +
                "\n" +
                "ul.social{\n" +
                "\tpadding: 0;\n" +
                "}\n" +
                "ul.social li{\n" +
                "\tdisplay: inline-block;\n" +
                "\tmargin-right: 10px;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "@media screen and (max-width: 500px) {\n" +
                "\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body width='50%' style='margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #f1f1f1;'>\n" +
                "\t<center style='width: 50%; background-color: #f1f1f1;'>\n" +
                "    <div style='display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;'>\n" +
                "      &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;\n" +
                "    </div>\n" +
                "    <div style='max-width: 600px; margin: 0 auto;' class='email-container'>\n" +
                "    \t<!-- BEGIN BODY -->\n" +
                "      <table align='center' role='presentation' cellspacing='0' cellpadding='0' border='0' width='50%' style='margin: auto;'>\n" +
                "      \t<tr>\n" +
                "          <td valign='top' class='bg_white' style='padding: 1em 2.5em 0 2.5em;'>\n" +
                "          \t<table role='presentation' border='0' cellpadding='0' cellspacing='0' width='100%'>\n" +
                "          \t\t<tr>\n" +
                "          \t\t\t<td class='logo' style='text-align: center;'>\n" +
                "\t\t\t            <h1><a href='#'>Eventer</a></h1><br />\n" +
                "            \t\t\t\t<h2>Kod bezpieczeństwa</h2>\n" +
                "            \t\t\t\t<p>"+message+"</p><br />\n" +
                "\t\t\t          </td>\n" +
                "          \t\t</tr>\n" +
                "          \t</table>\n" +
                "          </td>\n" +
                "\t      </tr><!-- end tr -->\n" +
                "      </table>\n" +
                "          </td>\n" +
                "        </tr><!-- end: tr -->\n" +
                "      </table>\n" +
                "\n" +
                "    </div>\n" +
                "  </center>\n" +
                "</body>\n" +
                "</html>";

        helper.setText(htmlContent, true); // Ustawiamy treść jako HTML

        emailSender.send(mimeMessage);
    }
}

