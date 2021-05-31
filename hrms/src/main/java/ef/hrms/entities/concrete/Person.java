package ef.hrms.entities.concrete;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the persons database table.
 * 
 */
@Data
@Entity
@Table(name = "persons")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;

	@Column(name = "birth_date")
	@NotNull
	@NotBlank
	@Past
	private LocalDate birthDate;

	private String email;

	@Column(name = "first_name")
	@NotNull
	@NotBlank
	private String firstName;

	@Column(name = "last_name")
	@NotNull
	@NotBlank
	private String lastName;

	@Column(name = "national_id")
	@NotNull
	@NotBlank
	private String nationalId;

	// bi-directional one-to-one association to Candidate
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<Candidate> candidates;

}
