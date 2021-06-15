package br.com.uppercomputer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uppercomputer.api.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByEmailIgnoreCase(String email);

}
