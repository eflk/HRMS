package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Document;

public interface DocumentDao extends JpaRepository<Document, Integer> {

}
