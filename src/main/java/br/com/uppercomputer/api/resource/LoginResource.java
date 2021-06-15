package br.com.uppercomputer.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uppercomputer.api.model.Admin;
import br.com.uppercomputer.api.model.to.AdminTO;
import br.com.uppercomputer.api.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginResource {
	
	@Autowired
	private LoginService service;
	
	@PostMapping
	public ResponseEntity<Admin> login(@RequestBody @Valid AdminTO admin) {
		return service.login(admin);
	}
	
	@PostMapping("/recover-password")
	public ResponseEntity<Admin> recuperarSenha(@RequestBody @Valid AdminTO admin) {
		return service.recoverPassword(admin);
	}
	
	@PostMapping("/change/{id}")
	public ResponseEntity<Admin> alterPasswd (@PathVariable String id, 
			@RequestBody @Valid AdminTO admin) {

		return service.changePassword(id, admin.getPassword());
	}
	
	@PostMapping("/code/{id}")
	public ResponseEntity<Admin> codeValidate (@PathVariable String id, 
			@RequestBody @Valid AdminTO admin) {

		return service.codeValidate(id, admin.getCode());
	}
}
