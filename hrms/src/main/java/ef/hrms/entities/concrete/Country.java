package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	private String code3;

	private String name;

	@Column(name="numeric_code")
	private int numericCode;

	//bi-directional many-to-one association to JobLocation
	@OneToMany(mappedBy="country")
	private List<JobLocation> jobLocations;

	public Country() {
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

	public String getCode3() {
		return this.code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumericCode() {
		return this.numericCode;
	}

	public void setNumericCode(int numericCode) {
		this.numericCode = numericCode;
	}

	public List<JobLocation> getJobLocations() {
		return this.jobLocations;
	}

	public void setJobLocations(List<JobLocation> jobLocations) {
		this.jobLocations = jobLocations;
	}

	public JobLocation addJobLocation(JobLocation jobLocation) {
		getJobLocations().add(jobLocation);
		jobLocation.setCountry(this);

		return jobLocation;
	}

	public JobLocation removeJobLocation(JobLocation jobLocation) {
		getJobLocations().remove(jobLocation);
		jobLocation.setCountry(null);

		return jobLocation;
	}

}