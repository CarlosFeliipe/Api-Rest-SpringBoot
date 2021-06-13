package br.com.uppercomputer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uppercomputer.api.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	public Project findByCode(String code);
}
