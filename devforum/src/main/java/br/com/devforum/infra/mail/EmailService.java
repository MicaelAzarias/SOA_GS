package br.com.devforum.infra.mail;

import br.com.devforum.infra.exception.RegraDeNegocioException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${devforum.mail.from}")
    private String emailOrigem;

    @Value("${devforum.mail.sender-name}")
    private String nomeRemetente;

    @Async
    public void enviarEmailNotificacao(String para, String assunto, String conteudo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailOrigem, nomeRemetente);
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(conteudo, true);

            mailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}