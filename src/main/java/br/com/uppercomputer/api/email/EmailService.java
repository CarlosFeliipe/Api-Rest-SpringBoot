package br.com.uppercomputer.api.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.uppercomputer.api.handleExcepitions.NegocioException;

@Service
public class EmailService {
	
	@Autowired 
	JavaMailSender mailSender;
	
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(mensagem, true);
            mailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException("Erro ao enviar e-mail");
        }
	}
}
