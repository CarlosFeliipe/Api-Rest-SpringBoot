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

import br.com.uppercomputer.api.model.Project;
import br.com.uppercomputer.api.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectResource {
	
	@Autowired
	private ProjectService service;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Project> salve(@RequestBody @Valid Project project) {
		return service.createProject(project);
	}
	
	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public List<Project> findAll() {
		return service.findAllProject();
	}
	
	@GetMapping("findOne/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Project> findByProject(@PathVariable Integer id) {
		return service.findProjectById(id);
	}
	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete(@PathVariable Integer id) {
		service.deleteProject(id);
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Project change(@RequestBody Project project) {
		return service.changeProject(project);
	}
}
