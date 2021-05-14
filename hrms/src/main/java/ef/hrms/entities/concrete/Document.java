package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the documents database table.
 * 
 */
@Entity
@Table(name="documents")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String extention;

	private byte[] file;

	private String name;

	private String url;

	//bi-directional many-to-one association to ApplicationDocument
	@OneToMany(mappedBy="document")
	private List<ApplicationDocument> applicationDocuments;

	public Document() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExtention() {
		return this.extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ApplicationDocument> getApplicationDocuments() {
		return this.applicationDocuments;
	}

	public void setApplicationDocuments(List<ApplicationDocument> applicationDocuments) {
		this.applicationDocuments = applicationDocuments;
	}

	public ApplicationDocument addApplicationDocument(ApplicationDocument applicationDocument) {
		getApplicationDocuments().add(applicationDocument);
		applicationDocument.setDocument(this);

		return applicationDocument;
	}

	public ApplicationDocument removeApplicationDocument(ApplicationDocument applicationDocument) {
		getApplicationDocuments().remove(applicationDocument);
		applicationDocument.setDocument(null);

		return applicationDocument;
	}

}