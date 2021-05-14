package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the e_resumes database table.
 * 
 */
@Entity
@Table(name="e_resumes")
@NamedQuery(name="EResume.findAll", query="SELECT e FROM EResume e")
public class EResume implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="resume_json")
	private String resumeJson;

	//bi-directional many-to-one association to Candidate
	@ManyToOne
	private Candidate candidate;

	public EResume() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResumeJson() {
		return this.resumeJson;
	}

	public void setResumeJson(String resumeJson) {
		this.resumeJson = resumeJson;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}