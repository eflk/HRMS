package ef.hrms.entities.concrete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password_hash")
	private String passwordHash;

	@Column(name = "password_salt")
	private String passwordSalt;

	@Column(name = "country_id")
	private int countryId;

	@Column(name = "language_id")
	private int languageId;

	public User() {

	}

	public User(int id, String username, String passwordHash, String passwordSalt, int countryId,
			int languageId) {
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = passwordSalt;
		this.countryId = countryId;
		this.languageId = languageId;
	}

}
