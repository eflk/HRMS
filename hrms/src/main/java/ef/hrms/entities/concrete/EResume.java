package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the e_resumes database table.
 * 
 */
@Data
@Entity
@Table(name="e_resumes")
@NamedQuery(name="EResume.findAll", query="SELECT e FROM EResume e")
@AllArgsConstructor
@NoArgsConstructor
public class EResume implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="resume_json")
	private String resumeJson;

	//bi-directional many-to-one association to Candidate
	@ManyToOne
	private Candidate candidate;


}