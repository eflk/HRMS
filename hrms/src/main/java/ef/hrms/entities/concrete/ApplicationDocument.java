package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the application_documents database table.
 * 
 */
@Entity
@Table(name="application_documents")
@NamedQuery(name="ApplicationDocument.findAll", query="SELECT a FROM ApplicationDocument a")
public class ApplicationDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Application
	@ManyToOne
	private Application application;

	//bi-directional many-to-one association to Document
	@ManyToOne
	private Document document;

	public ApplicationDocument() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}