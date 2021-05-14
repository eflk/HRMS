package ef.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ef.hrms.entities.concrete.Language;

public interface LanguageDao extends JpaRepository<Language, Integer> {

}
