package ef.hrms.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInDto {
	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String password;

}
