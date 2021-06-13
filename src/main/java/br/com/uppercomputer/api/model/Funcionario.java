package br.com.uppercomputer.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="TB_FUNCIONARIO")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "Name")
	private String name;
	
	@NotNull
	@Column(name = "RegistrationNumber")
	private String registrationNumber;
	
	@NotNull
	@Column(name = "Email")
	private String email;
	
	@NotNull
	@Column(name = "Salary")
	private String salary;
	
	@NotNull
	@Column(name = "HomePhone")
	private String homePhone;
	
	@NotNull
	@Column(name = "BusinessPhone")
	private String telefoneCormecial;
	
	@NotNull
	@Column(name = "CellPhone")
	private String cellPhone;
	
	@NotNull
	@Column(name = "Status")
	private String status;
	
	@NotNull
	@Column(name = "Ramal")
	private String ramal;
	
	@NotNull
	@Column(name = "Cep")
	private String cep;
	
	@NotNull
	@Column(name = "UF")
	private String uf;
	
	@NotNull
	@Column(name = "State")
	private String State;
	
	@NotNull
	@Column(name = "City")
	private String City;
	
	@NotNull
	@Column(name = "HomeAddress")
	private String homeAddress;
	
	@NotNull
	@Column(name = "Block")
	private String bloco;
	
	@NotNull
	@Column(name = "Complement")
	private String complement;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getTelefoneCormecial() {
		return telefoneCormecial;
	}

	public void setTelefoneCormecial(String telefoneCormecial) {
		this.telefoneCormecial = telefoneCormecial;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}