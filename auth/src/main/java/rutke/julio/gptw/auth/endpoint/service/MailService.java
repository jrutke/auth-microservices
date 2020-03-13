package rutke.julio.gptw.auth.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String email, String code){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Código de Autenticação");
        msg.setText("Seu código de Autenticação é: "+code);

        javaMailSender.send(msg);

        return true;
    }
}
