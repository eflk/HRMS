package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employers database table.
 * 
 */
@Entity
@Table(name="employers")
@NamedQuery(name="Employer.findAll", query="SELECT e FROM Employer e")
public class Employer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to Firm
	@ManyToOne
	private Firm firm;

	public Employer() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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