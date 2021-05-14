package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clients database table.
 * 
 */
@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="accounting_number")
	private String accountingNumber;

	@Column(name="user_id")
	private Integer userId;

	//bi-directional many-to-one association to Candidate
	@OneToMany(mappedBy="client")
	private List<Candidate> candidates;

	//bi-directional many-to-one association to Employer
	@OneToMany(mappedBy="client")
	private List<Employer> employers;

	//bi-directional many-to-one association to Recuiter
	@OneToMany(mappedBy="client")
	private List<Recuiter> recuiters;

	public Client() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountingNumber() {
		return this.accountingNumber;
	}

	public void setAccountingNumber(String accountingNumber) {
		this.accountingNumber = accountingNumber;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Candidate> getCandidates() {
		return this.candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Candidate addCandidate(Candidate candidate) {
		getCandidates().add(candidate);
		candidate.setClient(this);

		return candidate;
	}

	public Candidate removeCandidate(Candidate candidate) {
		getCandidates().remove(candidate);
		candidate.setClient(null);

		return candidate;
	}

	public List<Employer> getEmployers() {
		return this.employers;
	}

	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}

	public Employer addEmployer(Employer employer) {
		getEmployers().add(employer);
		employer.setClient(this);

		return employer;
	}

	public Employer removeEmployer(Employer employer) {
		getEmployers().remove(employer);
		employer.setClient(null);

		return employer;
	}

	public List<Recuiter> getRecuiters() {
		return this.recuiters;
	}

	public void setRecuiters(List<Recuiter> recuiters) {
		this.recuiters = recuiters;
	}

	public Recuiter addRecuiter(Recuiter recuiter) {
		getRecuiters().add(recuiter);
		recuiter.setClient(this);

		return recuiter;
	}

	public Recuiter removeRecuiter(Recuiter recuiter) {
		getRecuiters().remove(recuiter);
		recuiter.setClient(null);

		return recuiter;
	}

}