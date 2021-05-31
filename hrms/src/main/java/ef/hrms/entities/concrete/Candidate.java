package ef.hrms.entities.concrete;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * The persistent class for the candidates database table.
 * 
 */
@Data
@Entity
@Table(name="candidates")
@AllArgsConstructor
@NoArgsConstructor
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Application
	@OneToMany(mappedBy="candidate")
	private List<Application> applications;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to EResume
	@OneToMany(mappedBy="candidate")
	private List<EResume> EResumes;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;


}