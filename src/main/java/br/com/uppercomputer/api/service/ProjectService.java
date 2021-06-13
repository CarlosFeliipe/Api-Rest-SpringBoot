package br.com.uppercomputer.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uppercomputer.api.handleExcepitions.NegocioException;
import br.com.uppercomputer.api.model.Funcionario;
import br.com.uppercomputer.api.model.Project;
import br.com.uppercomputer.api.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository repository;
	
	public ResponseEntity<Project> createProject(Project project) {
		
		Project resultExists = repository.findByCode(project.getCode());
		
		if(resultExists == null) {
			
			try {
				this.save(project);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new NegocioException("Erro ao cadastrar projeto.");
			}		
			
		} else {
			throw new NegocioException("Projeto já cadastrado.");
		}
		
		return ResponseEntity.ok(project);
	}
	
	public void save(Project project) {
		try {
			repository.save(project);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao salvar projeto.");
		}
	}
	
	public Optional<Project> findProjectById(Integer id) {
		Optional<Project> project = repository.findById(id);
		return project;
	}
	
	public List<Project> findAllProject() {
		return repository.findAll();
	}
	
	public Project changeProject(Project project) {
		return repository.save(project);
	}
	
	public void deleteProject(Integer id) {
		if(repository.findById(id).isPresent()) {
			repository.deleteById(id);
		} else {
			throw new NegocioException("Funcionario não encontrado.");
		}
	}
	
}
