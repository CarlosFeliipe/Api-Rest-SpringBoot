package br.com.uppercomputer.api.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.uppercomputer.api.model.Admin;
import br.com.uppercomputer.api.model.Funcionario;
import br.com.uppercomputer.api.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminResource {
	
	@Autowired
	private AdminService service;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Admin> salve(@RequestBody @Valid Admin admin) {
		return service.createAdmin(admin);
	}
	
	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public List<Admin> findAllAdmin() {
		return service.findAllAdmin();
	}
	
	@GetMapping("findOne/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Admin> findByAdmin(@PathVariable Integer id) {
		return service.findAdminById(id);
	}
	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete(@PathVariable Integer id) {
		service.deleteAdmin(id);
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Admin change(@RequestBody Admin admin) {
		return service.changeAdmin(admin);
	}
}
