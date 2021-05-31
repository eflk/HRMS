package ef.hrms.entities.dtos;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
	private int id;
	private int status;

	@NotNull
	@NotBlank
	private int mailType;

	@NotNull
	@NotBlank
	@Email
	private String senderMailAddress;

	@NotNull
	@NotBlank
	@Email
	private List<String> to;

	@Email
	private List<String> cc;

	@Email
	private List<String> bcc;

	@NotNull
	@NotBlank
	private String subject;

	@NotNull
	@NotBlank
	private String body;

}
