package com.usermanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullName;
	private String email;
	private String address;
	private String qualification;
	private String password;
	@OneToOne//(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private UserDesignationInfo userDesignationInfo;

	/*@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
	private LocalDate joiningDate;*/
	
}
