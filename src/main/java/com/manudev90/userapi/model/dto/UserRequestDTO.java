package com.manudev90.userapi.model.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class UserRequestDTO {
	
	@NotBlank(message = "Campo name es requerido")
	private String name;
	@NotBlank(message = "Campo email es requerido")
	@Email(message = "Formato de email no valido")
	private String email;
	@NotBlank(message = "Campo pasword es requerido")
	private String password;
	@NotEmpty (message = "Campo phones es requerido")
    private List<PhoneDTO> phones;
	

	
	public UserRequestDTO() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}
	
	
	
	
}
