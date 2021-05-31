package ef.hrms.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import ef.hrms.entities.concrete.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {
	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	@Length(min = 6)
	private String password;

	@NotNull
	@NotBlank
	@Length(min = 6)
	private String password2;
	
	@NotNull
	@NotBlank
	private int countryId;

	@NotNull
	@NotBlank
	private int languageId;
	
	@NotNull
	private Person person;

}
