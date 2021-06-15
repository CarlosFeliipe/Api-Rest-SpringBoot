package br.com.uppercomputer.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uppercomputer.api.email.EmailService;
import br.com.uppercomputer.api.handleExcepitions.NegocioException;
import br.com.uppercomputer.api.model.Admin;
import br.com.uppercomputer.api.repository.AdminRepository;
import br.com.uppercomputer.utils.Utils;
import lombok.SneakyThrows;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository repository;
	
	@Autowired
	private EmailService emailService;
	
	public Admin findByEmailIgnoreCase(String email) {
		return repository.findByEmailIgnoreCase(email);
	}
	
	public Admin getUsuarioById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public void existeUsuario(Integer id) {
		if (!repository.existsById(id))
			throw new NegocioException("Admin não encontrado.");
	}
	
	public ResponseEntity<Admin> createAdmin(Admin admin) {
		
		Admin adminExists = findByEmailIgnoreCase(admin.getEmail());
		
		if(adminExists == null) {
			
			try {
				String code = Utils.generateCode();
				admin.setCode(code);
				admin.setPassword(Utils.md5(admin.getPassword()));
				admin.setStatus("ACTIVE");
				admin.setResetarSenha("N");
				
				String assunto = "Sistema Upper Computer - Confirmação de Cadastro";
				 
			    String mensagem = "<p>Olá "+ admin.getName() +", </p>";
			    mensagem += "<p>Confirmação de Cadastro</p>";
			    mensagem += "<p>Use esse código, para ativar seu usuário: "+ code + "</p>";
			    mensagem += "<p>Atenciosamente, </p>";
			    mensagem += "<p>Sistema Upper Computer</p>";
			    emailService.enviarEmail(admin.getEmail(), assunto, mensagem);
			    this.save(admin);
			    return ResponseEntity.ok(admin);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new NegocioException("Erro ao cadastrar Admin.");
			}		
			
		} else {
			throw new NegocioException("Admin já cadastrado.");
		}
	}
	
	public void save(Admin admin) {
		try {
			repository.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao salvar Admin.");
		}
	}
	
	public Optional<Admin> findAdminById(Integer id) {
		Optional<Admin> admin = repository.findById(id);
		return admin;
	}
	
	public List<Admin> findAllAdmin() {
		return repository.findAll();
	}
	
	public void deleteAdmin(Integer id) {
		if(repository.findById(id).isPresent()) {
			repository.deleteById(id);
		} else {
			throw new NegocioException("Admin não encontrado.");
		}
	}
	
	public Admin changeAdmin(Admin admin) {
		return repository.save(admin);
	}
	
	@SneakyThrows
	public ResponseEntity<Admin> update(Admin admin, String id) {
		Integer idUsuario = Integer.valueOf(id);
		this.existeUsuario(idUsuario);
		
		Admin usuarioBase = this.getUsuarioById(idUsuario);
		BeanUtils.copyProperties(admin, usuarioBase);
		admin.setId(idUsuario);
		this.save(admin);

		return ResponseEntity.ok(admin);
	}
	
	public ResponseEntity<Admin> changePassword(String id, String senha){
		
		Integer idAdmin = Integer.valueOf(id);
		this.existeUsuario(idAdmin);
		Admin admin = this.getUsuarioById(idAdmin);
		
		try {
			if(senha.length() < 6) {
				throw new NegocioException("Senha deve ter mais de 6 caracteres");
			} else {
				admin.setPassword(Utils.md5(senha));
				admin.setResetarSenha("N");
				this.update(admin, id);
			}
		} catch (NegocioException ne) {
			throw new NegocioException(ne.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao alterar senha.");
		}
	
		return ResponseEntity.ok(admin);
	}
	
	public ResponseEntity<Admin> codeValidate(String id, String code){
		
		Integer idAdmin = Integer.valueOf(id);
		this.existeUsuario(idAdmin);
		Admin admin = this.getUsuarioById(idAdmin);
		
		try {
			if(admin.getCode().equalsIgnoreCase(code)) {
				admin.setCode("");
				this.update(admin, id);
			} else {
				throw new NegocioException("Codigo invalido");
			}
		} catch (NegocioException ne) {
			throw new NegocioException(ne.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao validar codigo.");
		}
	
		return ResponseEntity.ok(admin);
	}
}
