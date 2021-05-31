package ef.hrms.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDto {
	private int id;

	@NotNull
	@NotBlank
	private int smsType;
	private int status;

	@NotNull
	@NotBlank
	private String from;

	@NotNull
	@NotBlank
	private String body;

}
