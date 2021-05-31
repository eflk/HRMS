package ef.hrms.entities.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVerificationDto {

	@NotNull
	@NotBlank
	@Email
	private String username;

	@NotNull
	@NotBlank
	private String verificationCode;
}
