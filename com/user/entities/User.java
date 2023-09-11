package com.user.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="microService_user")
@Data  //will generate getters setters constructors for the class as we are using lombok
@NoArgsConstructor  
@AllArgsConstructor
@Builder
public class User {
	@Id
	
	@Column(name = "ID")
	private String userId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "E-MAIL")
	private String email;
	@Column(name="ABOUT")
	private String about;

	@Transient
	//transient is used when we dont want a particular field to be stored in the database and want jpa
	//to ignore it.
	private List<Rating> ratings;

	
	
	

}
