package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the jobs database table.
 * 
 */
@Entity
@Table(name="jobs")
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	private String description;

	private String name;

	//bi-directional many-to-one association to Application
	@OneToMany(mappedBy="job")
	private List<Application> applications;

	//bi-directional many-to-one association to JobCategory
	@ManyToOne
	@JoinColumn(name="job_category_id")
	private JobCategory jobCategory;

	//bi-directional many-to-one association to JobLocation
	@ManyToOne
	@JoinColumn(name="job_location_id")
	private JobLocation jobLocation;

	//bi-directional many-to-one association to JobPlatform
	@ManyToOne
	@JoinColumn(name="job_platform_id")
	private JobPlatform jobPlatform;

	//bi-directional many-to-one association to JobPosition
	@ManyToOne
	@JoinColumn(name="job_position_id")
	private JobPosition jobPosition;

	//bi-directional many-to-one association to Recuiter
	@ManyToOne
	@JoinColumn(name="recruiter_id")
	private Recuiter recuiter;

	public Job() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public Application addApplication(Application application) {
		getApplications().add(application);
		application.setJob(this);

		return application;
	}

	public Application removeApplication(Application application) {
		getApplications().remove(application);
		application.setJob(null);

		return application;
	}

	public JobCategory getJobCategory() {
		return this.jobCategory;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

	public JobLocation getJobLocation() {
		return this.jobLocation;
	}

	public void setJobLocation(JobLocation jobLocation) {
		this.jobLocation = jobLocation;
	}

	public JobPlatform getJobPlatform() {
		return this.jobPlatform;
	}

	public void setJobPlatform(JobPlatform jobPlatform) {
		this.jobPlatform = jobPlatform;
	}

	public JobPosition getJobPosition() {
		return this.jobPosition;
	}

	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	public Recuiter getRecuiter() {
		return this.recuiter;
	}

	public void setRecuiter(Recuiter recuiter) {
		this.recuiter = recuiter;
	}

}