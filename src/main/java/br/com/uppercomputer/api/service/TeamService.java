package br.com.uppercomputer.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uppercomputer.api.handleExcepitions.NegocioException;
import br.com.uppercomputer.api.model.Team;
import br.com.uppercomputer.api.repository.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	TeamRepository repository;
	
	public ResponseEntity<Team> createTeam(Team team) {
		
		Team resultExists = repository.findByCode(team.getCode());
		
		if(resultExists == null) {
			
			try {
				this.save(team);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new NegocioException("Erro ao cadastrar equipe.");
			}		
			
		} else {
			throw new NegocioException("Equipe já cadastrada.");
		}
		
		return ResponseEntity.ok(team);
	}
	
	public void save(Team team) {
		try {
			repository.save(team);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao salvar a equipe.");
		}
	}
	
	public Optional<Team> findTeamById(Integer id) {
		Optional<Team> team = repository.findById(id);
		return team;
	}
	
	public List<Team> findAllTeam() {
		return repository.findAll();
	}
	
	public Team changeTeam(Team team) {
		return repository.save(team);
	}
	
	public void deleteTeam(Integer id) {
		if(repository.findById(id).isPresent()) {
			repository.deleteById(id);
		} else {
			throw new NegocioException("Equipe não encontrado.");
		}
	}
}
