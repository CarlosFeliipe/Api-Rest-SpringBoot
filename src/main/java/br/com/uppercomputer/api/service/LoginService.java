package br.com.uppercomputer.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uppercomputer.api.email.EmailService;
import br.com.uppercomputer.api.handleExcepitions.NegocioException;
import br.com.uppercomputer.api.model.Admin;
import br.com.uppercomputer.api.model.to.AdminTO;
import br.com.uppercomputer.utils.Utils;
import javassist.NotFoundException;

@Service
public class LoginService {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private EmailService emailService;
	
	public ResponseEntity<Admin> login(AdminTO adminTO) {
		
		Admin admin = service.findByEmailIgnoreCase(adminTO.getEmail());
		
		try {
			
			String senhaMd5 = Utils.md5(adminTO.getPassword());
			
			if(admin == null) {
				throw new NotFoundException("Admin não encontrado.");
				
			} else {
				if(admin.getStatus().equalsIgnoreCase("INATIVO")) {
					throw new  NegocioException("Não é possível realizar o login, pois o admin está inativo(a).");
				
				} else if(admin.getPassword().equals(senhaMd5) && senhaMd5 != "" && admin.getCode().equalsIgnoreCase("")) {	
					return ResponseEntity.ok(admin);
				} else {
					throw new  NegocioException("Ative o seu Login com o código enviado para o seu E-mail.");
				}
			}
			
		} catch (NegocioException ne) {
			throw new NegocioException(ne.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("E-mail ou senha incorreto(s).");
		}
	}
	
	public ResponseEntity<Admin> recoverPassword(AdminTO adminTO) {
		
		Admin admin = service.findByEmailIgnoreCase(adminTO.getEmail());
		
		try {
			if(admin == null) {
				throw new NotFoundException("Usuário não encontrado.");	
				
			} else {		
				if(admin.getEmail().equalsIgnoreCase(adminTO.getEmail()) && adminTO.getEmail() != "") {
					 String senha = Utils.generatePassword();
					 admin.setPassword(Utils.md5(senha));
					 admin.setResetarSenha("S");
					 service.update(admin, admin.getId().toString());
					 
					 String assunto = "Sistema Upper Computer - Alteração de Senha";
					 
				     String mensagem = "<p>Olá "+ admin.getName() +", </p>";
				     mensagem += "<p>A sua nova senha de acesso é: "+ senha + "</p>";
				     mensagem += "<p>Por motivos de segurança, após o acesso será solicitado que sua senha seja alterada.</p>";
				     mensagem += "<p>Atenciosamente, </p>";
				     mensagem += "<p>Sistema Upper Computer</p>";
				     emailService.enviarEmail(admin.getEmail(), assunto, mensagem);
				     return ResponseEntity.ok(admin);
				     
				} else {
					throw new  NegocioException("E-mail não existe.");
				}
			}
		} catch (NegocioException ne) {
			throw new NegocioException(ne.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("E-mail não existe.");
		}
	}
	
	public ResponseEntity<Admin> changePassword(String id, String senha) {	
		return service.changePassword(id, senha);	
	}
	
	public ResponseEntity<Admin> codeValidate(String id, String code) {	
		return service.codeValidate(id, code);	
	}
}
