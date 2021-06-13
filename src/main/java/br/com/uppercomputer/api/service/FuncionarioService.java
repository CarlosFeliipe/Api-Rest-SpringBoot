package br.com.uppercomputer.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uppercomputer.api.handleExcepitions.NegocioException;
import br.com.uppercomputer.api.model.Funcionario;
import br.com.uppercomputer.api.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository repository;
	
	public Funcionario findByEmailIgnoreCase(String email) {
		return repository.findByEmailIgnoreCase(email);
	}
	
	public Optional<Funcionario> findFuncionarioById(Integer id) {
		Optional<Funcionario> employee = repository.findById(id);
		return employee;
	}
	
	public List<Funcionario> findAllEmployee() {
		return repository.findAll();
	}
	
	public ResponseEntity<Funcionario> createEmployee(Funcionario employee) {
		
		Funcionario employeeExists = findByEmailIgnoreCase(employee.getEmail());
		
		if(employeeExists == null) {
			
			try {
				employee.setStatus("ACTIVE");
				this.save(employee);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new NegocioException("Erro ao cadastrar funcionario.");
			}		
			
		} else {
			throw new NegocioException("Funcionario já cadastrado.");
		}
		
		return ResponseEntity.ok(employee);
	}
	
	public void save(Funcionario employee) {
		try {
			repository.save(employee);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao salvar funcionario.");
		}
	}
	
	public void deleteEmployee(Integer id) {
		if(repository.findById(id).isPresent()) {
			repository.deleteById(id);
		} else {
			throw new NegocioException("Funcionario não encontrado.");
		}
	}
	
	public Funcionario changeEmployee(Funcionario employee) {
		return repository.save(employee);
	}
	
}
