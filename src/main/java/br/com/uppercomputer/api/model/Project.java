package br.com.uppercomputer.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name="TB_PROJECT")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "Code")
	private String code;
	
	@NotNull
	@Column(name = "Name")
	private String name;
	
	@Column(name = "StartedData")
	private String startedData;
	 
	@Column(name = "ExpectedEndDate")
	private String expectedEndDate;
	
	@Column(name = "EndData")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private String endData;
	
	@NotNull
	@Column(name = "NumberHoursForecast")
	private String numberHoursForecast;
	
	@NotNull
	@Column(name = "NumberRealHours")
	private String numberRealHours;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartedData() {
		return startedData;
	}

	public void setStartedData(String startedData) {
		this.startedData = startedData;
	}

	public String getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(String expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public String getEndData() {
		return endData;
	}

	public void setEndData(String endData) {
		this.endData = endData;
	}

	public String getNumberHoursForecast() {
		return numberHoursForecast;
	}

	public void setNumberHoursForecast(String numberHoursForecast) {
		this.numberHoursForecast = numberHoursForecast;
	}

	public String getNumberRealHours() {
		return numberRealHours;
	}

	public void setNumberRealHours(String numberRealHours) {
		this.numberRealHours = numberRealHours;
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
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
