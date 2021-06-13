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

import br.com.uppercomputer.api.model.Funcionario;import br.com.uppercomputer.api.service.FuncionarioService;

@RestController
@RequestMapping("/employee")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService service;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Funcionario> salve(@RequestBody @Valid Funcionario employee) {
		return service.createEmployee(employee);
	}
	
	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> findAllEmployee() {
		return service.findAllEmployee();
	}
	
	@GetMapping("findOne/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Funcionario> findByEmployee(@PathVariable Integer id) {
		return service.findFuncionarioById(id);
	}
	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete(@PathVariable Integer id) {
		service.deleteEmployee(id);
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario change(@RequestBody Funcionario employee) {
		return service.changeEmployee(employee);
	}
}
