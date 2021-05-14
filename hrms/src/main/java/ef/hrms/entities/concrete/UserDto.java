package ef.hrms.entities.concrete;

import lombok.Data;

@Data
public class UserDto {
	private String email;
	private String password;
	private Integer countryId;
	private Integer languageId;

	public UserDto(String email, String password, Integer countryId, Integer languageId) {
		this.email = email;
		this.password = password;
		this.countryId = countryId;
		this.languageId = languageId;
	}

}
