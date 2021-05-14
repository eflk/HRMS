package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.ApplicationDocument;

public interface ApplicationDocumentDao extends JpaRepository<ApplicationDocument, Integer> {

}