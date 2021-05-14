package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the applications database table.
 * 
 */
@Entity
@Table(name="applications")
@NamedQuery(name="Application.findAll", query="SELECT a FROM Application a")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date createDate;

	//bi-directional many-to-one association to ApplicationDocument
	@OneToMany(mappedBy="application")
	private List<ApplicationDocument> applicationDocuments;

	//bi-directional many-to-one association to Candidate
	@ManyToOne
	private Candidate candidate;

	//bi-directional many-to-one association to Job
	@ManyToOne
	private Job job;

	public Application() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<ApplicationDocument> getApplicationDocuments() {
		return this.applicationDocuments;
	}

	public void setApplicationDocuments(List<ApplicationDocument> applicationDocuments) {
		this.applicationDocuments = applicationDocuments;
	}

	public ApplicationDocument addApplicationDocument(ApplicationDocument applicationDocument) {
		getApplicationDocuments().add(applicationDocument);
		applicationDocument.setApplication(this);

		return applicationDocument;
	}

	public ApplicationDocument removeApplicationDocument(ApplicationDocument applicationDocument) {
		getApplicationDocuments().remove(applicationDocument);
		applicationDocument.setApplication(null);

		return applicationDocument;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}