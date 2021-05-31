package ef.hrms.entities.concrete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private int id;

	@Column(name = "username")
	@NotNull
	@NotBlank
	@Email
	private String username;

	@Column(name = "password_hash")
	@NotNull
	@NotBlank
	private String passwordHash;

	@Column(name = "password_salt")
	@NotNull
	@NotBlank
	private String passwordSalt;

	@Column(name = "country_id")
	@NotNull
	@NotBlank
	private int countryId;

	@Column(name = "language_id")
	@NotNull
	@NotBlank
	private int languageId;

	@Column(name = "status")
	@NotNull
	@NotBlank
	private int status;


}
