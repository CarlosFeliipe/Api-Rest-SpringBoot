package br.com.uppercomputer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uppercomputer.api.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{
	public Team findByCode(String code);

}
