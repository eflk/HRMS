package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the candidates database table.
 * 
 */
@Entity
@Table(name="candidates")
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Application
	@OneToMany(mappedBy="candidate")
	private List<Application> applications;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to EResume
	@OneToMany(mappedBy="candidate")
	private List<EResume> EResumes;

	//bi-directional one-to-one association to Person
	@OneToOne
	private Person person;

	public Candidate() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public Application addApplication(Application application) {
		getApplications().add(application);
		application.setCandidate(this);

		return application;
	}

	public Application removeApplication(Application application) {
		getApplications().remove(application);
		application.setCandidate(null);

		return application;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<EResume> getEResumes() {
		return this.EResumes;
	}

	public void setEResumes(List<EResume> EResumes) {
		this.EResumes = EResumes;
	}

	public EResume addEResume(EResume EResume) {
		getEResumes().add(EResume);
		EResume.setCandidate(this);

		return EResume;
	}

	public EResume removeEResume(EResume EResume) {
		getEResumes().remove(EResume);
		EResume.setCandidate(null);

		return EResume;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}