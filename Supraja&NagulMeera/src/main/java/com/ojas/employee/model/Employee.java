package com.ojas.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "empcrud")
public class Employee {
	@Id
	@NotNull(message="EMployee Id should not be null")
	private Integer id;
	@NotBlank(message = "Employee name should not be null")
	@Pattern(regexp = "^[aA-zZ_ ]{2,30}$",message="Name should not contain any special characters")
	private String name;
	private Integer sal;
	@NotBlank(message = "Employee city should not be null")
	private String city;
	@NotNull(message="Employee Phone number should not be null")


	private Long phone;

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

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sal=" + sal + ", city=" + city + ", phone=" + phone + "]";
	}

	
}
