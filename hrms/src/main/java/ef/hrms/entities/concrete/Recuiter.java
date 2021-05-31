package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the recuiters database table.
 * 
 */
@Entity
@Table(name="recuiters")
@NamedQuery(name="Recuiter.findAll", query="SELECT r FROM Recuiter r")
public class Recuiter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="recuiter")
	private List<Job> jobs;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to Firm
	@ManyToOne
	private Firm firm;

	public Recuiter() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setRecuiter(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setRecuiter(null);

		return job;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Firm getFirm() {
		return this.firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}

}