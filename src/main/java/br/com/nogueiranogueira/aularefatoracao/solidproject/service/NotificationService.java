package br.com.nogueiranogueira.aularefatoracao.solidproject.service;


import br.com.nogueiranogueira.aularefatoracao.solidproject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailBoasVindas(String email, String nome) {

        String assunto = "Bem-vindo ao nosso sistema!";
        String mensagem = "Olá! Obrigado por se cadastrar em nosso sistema.";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(assunto);
        mailMessage.setText(mensagem);

        mailSender.send(mailMessage);

    }

}
