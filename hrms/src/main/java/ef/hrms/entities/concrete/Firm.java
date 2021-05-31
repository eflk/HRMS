package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the firms database table.
 * 
 */
@Entity
@Table(name="firms")
@NamedQuery(name="Firm.findAll", query="SELECT f FROM Firm f")
public class Firm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="fax_number")
	private String faxNumber;

	private String name;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="tax_number")
	private String taxNumber;

	@Column(name="tax_office")
	private String taxOffice;

	private String website;

	//bi-directional many-to-one association to Employer
	@OneToMany(mappedBy="firm")
	private List<Employer> employers;

	//bi-directional many-to-one association to Recuiter
	@OneToMany(mappedBy="firm")
	private List<Recuiter> recuiters;

	public Firm() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTaxNumber() {
		return this.taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getTaxOffice() {
		return this.taxOffice;
	}

	public void setTaxOffice(String taxOffice) {
		this.taxOffice = taxOffice;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Employer> getEmployers() {
		return this.employers;
	}

	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}

	public Employer addEmployer(Employer employer) {
		getEmployers().add(employer);
		employer.setFirm(this);

		return employer;
	}

	public Employer removeEmployer(Employer employer) {
		getEmployers().remove(employer);
		employer.setFirm(null);

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
		recuiter.setFirm(this);

		return recuiter;
	}

	public Recuiter removeRecuiter(Recuiter recuiter) {
		getRecuiters().remove(recuiter);
		recuiter.setFirm(null);

		return recuiter;
	}

}