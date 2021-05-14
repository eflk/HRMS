package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the job_locations database table.
 * 
 */
@Entity
@Table(name="job_locations")
@NamedQuery(name="JobLocation.findAll", query="SELECT j FROM JobLocation j")
public class JobLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="full_address")
	private String fullAddress;

	@Column(name="town_id")
	private Integer townId;

	//bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="jobLocation")
	private List<Job> jobs;

	public JobLocation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullAddress() {
		return this.fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Integer getTownId() {
		return this.townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setJobLocation(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setJobLocation(null);

		return job;
	}

}