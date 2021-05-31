package ef.hrms.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationDto {
	private int id;
	private int status;
	
	@NotNull
	@NotBlank
	private int notificationType;
	
	@NotNull
	@NotBlank
	private String subscriberId;
	
	@NotNull
	@NotBlank
	private String subject;
	
	@NotNull
	@NotBlank
	private String body;

}
