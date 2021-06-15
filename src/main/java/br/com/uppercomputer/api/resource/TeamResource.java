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

import br.com.uppercomputer.api.model.Team;
import br.com.uppercomputer.api.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamResource {
	
	@Autowired
	private TeamService service;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Team> salve(@RequestBody @Valid Team team) {
		return service.createTeam(team);
	}
	
	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public List<Team> findAll() {
		return service.findAllTeam();
	}
	
	@GetMapping("findOne/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Team> findByProject(@PathVariable Integer id) {
		return service.findTeamById(id);
	}
	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete(@PathVariable Integer id) {
		service.deleteTeam(id);
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Team change(@RequestBody Team team) {
		return service.changeTeam(team);
	}
}
